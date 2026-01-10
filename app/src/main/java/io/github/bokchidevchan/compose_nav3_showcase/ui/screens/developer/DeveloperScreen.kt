package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.developer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
fun DeveloperScreen(
    onBackClick: () -> Unit,
    onNavigateToDebugInfo: () -> Unit,
    onNavigateToFeatureFlags: () -> Unit,
    viewModel: DeveloperViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Developer options",
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
                SettingsCategory(title = "Debugging")
            }

            item {
                SettingsSwitch(
                    title = "Enable logging",
                    subtitle = "Log app events to console",
                    icon = Icons.Default.Terminal,
                    checked = state.loggingEnabled,
                    onCheckedChange = { viewModel.processIntent(DeveloperIntent.SetLogging(it)) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsSwitch(
                    title = "Debug overlays",
                    subtitle = "Show debug information on screen",
                    icon = Icons.Default.Layers,
                    checked = state.debugOverlaysEnabled,
                    onCheckedChange = { viewModel.processIntent(DeveloperIntent.SetDebugOverlays(it)) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsItem(
                    title = "Debug info",
                    subtitle = "View device and app information",
                    icon = Icons.Default.BugReport,
                    onClick = onNavigateToDebugInfo
                )
            }

            item {
                SettingsCategory(title = "Experiments")
            }

            item {
                SettingsItem(
                    title = "Feature flags",
                    subtitle = "Toggle experimental features",
                    icon = Icons.Default.Flag,
                    onClick = onNavigateToFeatureFlags
                )
            }
        }
    }
}
