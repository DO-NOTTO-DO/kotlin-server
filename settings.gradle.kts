rootProject.name = "nottodo-kotlin"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "commonSpring",
    "common",

    "application:mission",

    "persistence:rdb",
)
