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

    "api",

    "application:mission",

    "persistence:rdb",
)
