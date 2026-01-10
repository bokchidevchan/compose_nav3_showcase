package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.developer.debug

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import io.github.bokchidevchan.compose_nav3_showcase.BuildConfig
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsCategory
import io.github.bokchidevchan.compose_nav3_showcase.ui.components.SettingsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DebugInfoScreen(
    onBackClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SettingsTopAppBar(
                title = "Debug info",
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            SettingsCategory(title = "Device Information")

            InfoCard {
                InfoRow("Manufacturer", Build.MANUFACTURER)
                InfoRow("Model", Build.MODEL)
                InfoRow("Device", Build.DEVICE)
                InfoRow("Android Version", Build.VERSION.RELEASE)
                InfoRow("API Level", Build.VERSION.SDK_INT.toString())
                InfoRow("Build ID", Build.ID)
            }

            SettingsCategory(title = "App Information")

            InfoCard {
                InfoRow("Package", BuildConfig.APPLICATION_ID)
                InfoRow("Version Name", BuildConfig.VERSION_NAME)
                InfoRow("Version Code", BuildConfig.VERSION_CODE.toString())
                InfoRow("Build Type", BuildConfig.BUILD_TYPE)
                InfoRow("Debug", BuildConfig.DEBUG.toString())
            }

            SettingsCategory(title = "Screen Information")

            InfoCard {
                InfoRow("Screen Width", "${configuration.screenWidthDp} dp")
                InfoRow("Screen Height", "${configuration.screenHeightDp} dp")
                InfoRow("Density DPI", "${configuration.densityDpi}")
                InfoRow("Font Scale", "${configuration.fontScale}")
            }

            SettingsCategory(title = "Navigation 3 Info")

            InfoCard {
                InfoRow("Nav3 Version", "1.0.0")
                InfoRow("Back Stack Type", "SnapshotStateList")
                InfoRow("NavKey Type", "Type-safe (Serializable)")
                InfoRow("Predictive Back", "Enabled")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InfoCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
