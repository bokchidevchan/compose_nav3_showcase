package io.github.bokchidevchan.compose_nav3_showcase.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import io.github.bokchidevchan.compose_nav3_showcase.BuildConfig
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.About
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.DebugInfo
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.DeveloperOptions
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.Display
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.FeatureFlags
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.LicenseDetail
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.Licenses
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.MainSettings
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.Storage
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.StorageData
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.StorageItemDetail
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.ThemeDetail
import io.github.bokchidevchan.compose_nav3_showcase.core.navigation.ThemeSelection
import io.github.bokchidevchan.compose_nav3_showcase.feature.about.AboutScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.about.AppVersionInfo
import io.github.bokchidevchan.compose_nav3_showcase.feature.about.licenses.LicenseDetailScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.about.licenses.LicensesScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.developer.DeveloperScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.developer.debug.AppBuildInfo
import io.github.bokchidevchan.compose_nav3_showcase.feature.developer.debug.DebugInfoScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.developer.debug.FeatureFlagsScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.display.DisplayScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.display.theme.ThemeDetailScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.display.theme.ThemeSelectionScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.main.MainScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.storage.StorageScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.storage.data.StorageDataScreen
import io.github.bokchidevchan.compose_nav3_showcase.feature.storage.data.StorageItemDetailScreen

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<Any>(MainSettings) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally { it } + fadeIn() togetherWith
                slideOutHorizontally { -it / 3 } + fadeOut()
        },
        popTransitionSpec = {
            slideInHorizontally { -it / 3 } + fadeIn() togetherWith
                slideOutHorizontally { it } + fadeOut()
        },
        predictivePopTransitionSpec = {
            slideInHorizontally { -it / 3 } + fadeIn() togetherWith
                slideOutHorizontally { it } + fadeOut()
        },
        entryProvider = entryProvider {
            // Main Settings
            entry<MainSettings> {
                MainScreen(
                    onNavigateToDisplay = { backStack.add(Display) },
                    onNavigateToStorage = { backStack.add(Storage) },
                    onNavigateToAbout = { backStack.add(About) },
                    onNavigateToDeveloper = { backStack.add(DeveloperOptions) }
                )
            }

            // Display Category
            entry<Display> {
                DisplayScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onNavigateToThemeSelection = { backStack.add(ThemeSelection) }
                )
            }

            entry<ThemeSelection> {
                ThemeSelectionScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onThemeSelected = { mode -> backStack.add(ThemeDetail(mode)) }
                )
            }

            entry<ThemeDetail> { key ->
                ThemeDetailScreen(
                    themeMode = key.themeMode,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }

            // Storage Category
            entry<Storage> {
                StorageScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onNavigateToStorageData = { backStack.add(StorageData) }
                )
            }

            entry<StorageData> {
                StorageDataScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onItemClick = { id -> backStack.add(StorageItemDetail(id)) }
                )
            }

            entry<StorageItemDetail> { key ->
                StorageItemDetailScreen(
                    itemId = key.itemId,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }

            // About Category
            entry<About> {
                AboutScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onNavigateToLicenses = { backStack.add(Licenses) },
                    appInfo = AppVersionInfo(
                        versionName = BuildConfig.VERSION_NAME,
                        versionCode = BuildConfig.VERSION_CODE,
                        buildType = BuildConfig.BUILD_TYPE
                    )
                )
            }

            entry<Licenses> {
                LicensesScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onLicenseClick = { name -> backStack.add(LicenseDetail(name)) }
                )
            }

            entry<LicenseDetail> { key ->
                LicenseDetailScreen(
                    libraryName = key.libraryName,
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }

            // Developer Options
            entry<DeveloperOptions> {
                DeveloperScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    onNavigateToDebugInfo = { backStack.add(DebugInfo) },
                    onNavigateToFeatureFlags = { backStack.add(FeatureFlags) }
                )
            }

            entry<DebugInfo> {
                DebugInfoScreen(
                    appInfo = AppBuildInfo(
                        applicationId = BuildConfig.APPLICATION_ID,
                        versionName = BuildConfig.VERSION_NAME,
                        versionCode = BuildConfig.VERSION_CODE,
                        buildType = BuildConfig.BUILD_TYPE,
                        isDebug = BuildConfig.DEBUG
                    ),
                    onBackClick = { backStack.removeLastOrNull() }
                )
            }

            entry<FeatureFlags> {
                FeatureFlagsScreen(onBackClick = { backStack.removeLastOrNull() })
            }
        }
    )
}
