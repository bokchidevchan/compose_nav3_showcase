package io.github.bokchidevchan.compose_nav3_showcase.feature.about.licenses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsItem
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsTopAppBar

data class Library(
    val name: String,
    val license: String
)

private val libraries = listOf(
    Library("Jetpack Compose", "Apache 2.0"),
    Library("Navigation 3", "Apache 2.0"),
    Library("Room Database", "Apache 2.0"),
    Library("Material 3", "Apache 2.0"),
    Library("Kotlin Coroutines", "Apache 2.0"),
    Library("Kotlinx Serialization", "Apache 2.0"),
    Library("AndroidX Core KTX", "Apache 2.0"),
    Library("AndroidX Lifecycle", "Apache 2.0"),
    Library("AndroidX Activity Compose", "Apache 2.0"),
    Library("Splash Screen API", "Apache 2.0")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensesScreen(
    onBackClick: () -> Unit,
    onLicenseClick: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Open source licenses",
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
            items(libraries) { library ->
                SettingsItem(
                    title = library.name,
                    subtitle = library.license,
                    onClick = { onLicenseClick(library.name) }
                )
                HorizontalDivider()
            }
        }
    }
}
