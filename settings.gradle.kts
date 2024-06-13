rootProject.name = "nottodo-kotlin"

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "common-spring",
    "common",

    "api",

    "application:mission",
    "application:quote",
    "application:recommend",
    "application:user",

    "persistence:rdb",
)
