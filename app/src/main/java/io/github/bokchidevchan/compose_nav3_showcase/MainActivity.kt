package io.github.bokchidevchan.compose_nav3_showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.github.bokchidevchan.compose_nav3_showcase.navigation.AppNavigation
import io.github.bokchidevchan.compose_nav3_showcase.ui.theme.ComposeNav3ShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ComposeNav3ShowcaseTheme {
                AppNavigation()
            }
        }
    }
}
