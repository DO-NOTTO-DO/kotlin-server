import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Id.SPRING_BOOT) version Versions.SPRING_BOOT apply false
    id(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT) version Versions.SPRING_DEPENDENCY_MANAGEMENT
    id(Plugins.Id.KSP) version Versions.KSP
    id(Plugins.Id.KTLINT) version Versions.KTLINT
    id(Plugins.Id.KTLINT_IDEA) version Versions.KTLINT
    id(Plugins.Id.ASCII_DOCTOR) version Versions.ASCII_DOCTOR

    kotlin(Plugins.Modules.JVM) version Versions.KOTLIN
    kotlin(Plugins.Modules.SPRING) version Versions.KOTLIN
    kotlin(Plugins.Modules.JPA) version Versions.KOTLIN apply false
    kotlin(Plugins.Modules.KAPT) version Versions.KOTLIN
}

buildscript {
    dependencies {
        classpath(kotlin(Plugins.Modules.GRADLE_PLUGIN, Versions.KOTLIN))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

allprojects {
    apply {
        plugin<JavaLibraryPlugin>()
        plugin(Plugins.Id.KOTLIN_JVM)
    }

    repositories {
        mavenCentral()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.withType<Test> {
        maxHeapSize = "2048M"
        jvmArgs("-Dspring.test.context.cache.maxSize=1")
        useJUnitPlatform()
    }
}

subprojects {
    dependencies {
        implementation(Dependencies.Kotlin.REFLECT)
        implementation(Dependencies.Kotlin.STDLIB_JDK8)

        implementation(Dependencies.Google.GSON)
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Versions.JAVA
        }
    }
}

val springProjects = listOf(
    projects.common,
    projects.commonSpring,
    projects.api,
    projects.application.mission,
    projects.application.quote,
    projects.application.recommend,
    projects.persistence.rdb
).map { it.dependencyProject }

configure(springProjects) {
    apply {
        plugin(Plugins.Id.SPRING_BOOT)
        plugin(Plugins.Id.SPRING_DEPENDENCY_MANAGEMENT)
    }

    dependencies {
        implementation(Dependencies.Spring.Boot.WEB)

        implementation(Dependencies.Spring.Boot.VALIDATION)
        implementation(Dependencies.Spring.Boot.CONFIGURATION_PROCESSOR)
        annotationProcessor(Dependencies.Spring.Boot.CONFIGURATION_PROCESSOR)

        testRuntimeOnly(Dependencies.Database.H2)
    }
}
