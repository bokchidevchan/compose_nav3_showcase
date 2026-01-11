package io.github.bokchidevchan.compose_nav3_showcase.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsItem
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.components.SettingsLargeTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToDisplay: () -> Unit,
    onNavigateToStorage: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToDeveloper: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                MainEffect.NavigateToDisplay -> onNavigateToDisplay()
                MainEffect.NavigateToStorage -> onNavigateToStorage()
                MainEffect.NavigateToAbout -> onNavigateToAbout()
                MainEffect.NavigateToDeveloper -> onNavigateToDeveloper()
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsLargeTopAppBar(
                title = "Settings",
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
                SettingsItem(
                    title = "Display",
                    subtitle = "Theme, colors, appearance",
                    icon = Icons.Default.Palette,
                    onClick = { viewModel.processIntent(MainIntent.NavigateToDisplay) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsItem(
                    title = "Storage",
                    subtitle = "Manage app data and cache",
                    icon = Icons.Default.Storage,
                    onClick = { viewModel.processIntent(MainIntent.NavigateToStorage) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsItem(
                    title = "About",
                    subtitle = "App info and licenses",
                    icon = Icons.Default.Info,
                    onClick = { viewModel.processIntent(MainIntent.NavigateToAbout) }
                )
            }

            item { HorizontalDivider() }

            item {
                SettingsItem(
                    title = "Developer options",
                    subtitle = "Debug settings and feature flags",
                    icon = Icons.Default.Build,
                    onClick = { viewModel.processIntent(MainIntent.NavigateToDeveloper) }
                )
            }
        }
    }
}
