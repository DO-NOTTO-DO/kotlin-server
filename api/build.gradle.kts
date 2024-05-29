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

dependencies {
    implementation(projects.persistence.rdb)

    implementation(projects.application.mission)
    implementation(projects.application.quote)

    implementation(projects.common)
    implementation(projects.commonSpring)

    implementation(Dependencies.Spring.Boot.WEB)

//    implementation(Dependencies.Spring.Boot.SECURITY)
    implementation(Dependencies.Spring.Boot.JAKARTA_XML)

    testImplementation(Dependencies.Spring.Rest.REST_ASSURED)
    testImplementation(Dependencies.Spring.RestDocs.MOCK_MVC)
}
