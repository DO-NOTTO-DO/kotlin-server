import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_BOOT)
    id(Plugins.Id.ASCII_DOCTOR)

    kotlin(Plugins.Modules.SPRING)
    kotlin(Plugins.Modules.JPA)
}

tasks {
    getByName<Jar>("jar") {
        enabled = false
    }

    getByName<BootJar>("bootJar") {
        enabled = true
    }
}

val copyYml = tasks.register<Copy>("copyYml") {
    from("../server-secret/application-jwt.yml")
    into("src/main/resources/")
}

tasks.processResources {
    dependsOn(copyYml)
}

dependencies {
    implementation(projects.persistence.rdb)

    implementation(projects.application.mission)
    implementation(projects.application.quote)
    implementation(projects.application.recommend)

    implementation(projects.common)
    implementation(projects.commonSpring)

    implementation(Dependencies.Spring.Boot.WEB)
    implementation(Dependencies.Spring.Boot.SECURITY)
    implementation(Dependencies.Spring.Boot.JAKARTA_XML)

    implementation(Dependencies.JWT.JWT_API)
    runtimeOnly(Dependencies.JWT.JWT_IMPL)
    runtimeOnly(Dependencies.JWT.JWT_JACKSON)

    testImplementation(Dependencies.Spring.Rest.REST_ASSURED)
    testImplementation(Dependencies.Spring.RestDocs.MOCK_MVC)
}
