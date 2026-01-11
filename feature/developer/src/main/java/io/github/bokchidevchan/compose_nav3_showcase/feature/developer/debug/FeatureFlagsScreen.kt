package io.github.bokchidevchan.compose_nav3_showcase.feature.developer.debug

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsCategory
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsSwitch
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureFlagsScreen(
    onBackClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var animationsEnabled by remember { mutableStateOf(true) }
    var experimentalUI by remember { mutableStateOf(false) }
    var performanceMode by remember { mutableStateOf(false) }
    var betaFeatures by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Feature flags",
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SettingsCategory(title = "UI Features")
            }

            item {
                SettingsSwitch(
                    title = "Animations",
                    subtitle = "Enable UI animations and transitions",
                    icon = Icons.Default.Animation,
                    checked = animationsEnabled,
                    onCheckedChange = { animationsEnabled = it }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsSwitch(
                    title = "Experimental UI",
                    subtitle = "Try new UI components (may be unstable)",
                    icon = Icons.Default.Science,
                    checked = experimentalUI,
                    onCheckedChange = { experimentalUI = it }
                )
            }

            item {
                SettingsCategory(title = "Performance")
            }

            item {
                SettingsSwitch(
                    title = "Performance mode",
                    subtitle = "Optimize for speed over visual quality",
                    icon = Icons.Default.Speed,
                    checked = performanceMode,
                    onCheckedChange = { performanceMode = it }
                )
            }

            item {
                SettingsCategory(title = "Beta")
            }

            item {
                SettingsSwitch(
                    title = "Beta features",
                    subtitle = "Enable features still in development",
                    icon = Icons.Default.AutoAwesome,
                    checked = betaFeatures,
                    onCheckedChange = { betaFeatures = it }
                )
            }
        }
    }
}
