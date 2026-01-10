package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.display

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsCategory
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsItem
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsSwitch
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayScreen(
    onBackClick: () -> Unit,
    onNavigateToThemeSelection: () -> Unit,
    viewModel: DisplayViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                DisplayEffect.NavigateToThemeSelection -> onNavigateToThemeSelection()
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Display",
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
                SettingsCategory(title = "Appearance")
            }

            item {
                SettingsItem(
                    title = "Theme",
                    subtitle = state.themeMode.name.lowercase().replaceFirstChar { it.uppercase() },
                    icon = Icons.Default.Brightness6,
                    onClick = { viewModel.processIntent(DisplayIntent.NavigateToThemeSelection) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsSwitch(
                    title = "Dynamic colors",
                    subtitle = "Apply colors from wallpaper",
                    icon = Icons.Default.Palette,
                    checked = state.dynamicColorsEnabled,
                    onCheckedChange = { viewModel.processIntent(DisplayIntent.SetDynamicColors(it)) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsSwitch(
                    title = "High contrast",
                    subtitle = "Increase color contrast",
                    icon = Icons.Default.Contrast,
                    checked = state.highContrastEnabled,
                    onCheckedChange = { viewModel.processIntent(DisplayIntent.SetHighContrast(it)) }
                )
            }
        }
    }
}
