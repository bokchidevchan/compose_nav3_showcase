pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Compose-Nav3-Showcase"

include(":app")

// Core modules
include(":core:data")
include(":core:ui")
include(":core:navigation")

// Feature modules
include(":feature:main")
include(":feature:display")
include(":feature:storage")
include(":feature:about")
include(":feature:developer")
