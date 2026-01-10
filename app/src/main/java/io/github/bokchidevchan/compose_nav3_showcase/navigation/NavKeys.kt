package io.github.bokchidevchan.compose_nav3_showcase.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// ========================================
// Root/Main Screen
// ========================================

@Serializable
data object MainSettings : NavKey

// ========================================
// Display Category
// ========================================

@Serializable
data object Display : NavKey

@Serializable
data object ThemeSelection : NavKey

@Serializable
data class ThemeDetail(val themeMode: String) : NavKey

// ========================================
// Storage Category
// ========================================

@Serializable
data object Storage : NavKey

@Serializable
data object StorageData : NavKey

@Serializable
data class StorageItemDetail(val itemId: Long) : NavKey

// ========================================
// About Category
// ========================================

@Serializable
data object About : NavKey

@Serializable
data object Licenses : NavKey

@Serializable
data class LicenseDetail(val libraryName: String) : NavKey

// ========================================
// Developer Options Category
// ========================================

@Serializable
data object DeveloperOptions : NavKey

@Serializable
data object DebugInfo : NavKey

@Serializable
data object FeatureFlags : NavKey
