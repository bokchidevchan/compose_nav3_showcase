package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.display.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.bokchidevchan.compose_nav3_showcase.data.model.ThemeMode
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsItem
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionScreen(
    onBackClick: () -> Unit,
    onThemeSelected: (String) -> Unit,
    viewModel: ThemeSelectionViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val themes = listOf(
        Triple(ThemeMode.LIGHT, "Light", Icons.Default.BrightnessHigh),
        Triple(ThemeMode.DARK, "Dark", Icons.Default.Brightness4),
        Triple(ThemeMode.SYSTEM, "System default", Icons.Default.BrightnessAuto)
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Theme",
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
            items(themes) { (mode, title, icon) ->
                SettingsItem(
                    title = title,
                    icon = icon,
                    onClick = {
                        viewModel.processIntent(ThemeSelectionIntent.SelectTheme(mode))
                        onThemeSelected(mode.name)
                    },
                    trailing = {
                        if (state.selectedTheme == mode) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected"
                            )
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}
