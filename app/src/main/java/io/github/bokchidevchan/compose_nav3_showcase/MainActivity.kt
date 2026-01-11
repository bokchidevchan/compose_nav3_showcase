package io.github.bokchidevchan.compose_nav3_showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.theme.ComposeNav3ShowcaseTheme
import io.github.bokchidevchan.compose_nav3_showcase.navigation.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ComposeNav3ShowcaseTheme {
                AppNavigation()
            }
        }
    }
}
