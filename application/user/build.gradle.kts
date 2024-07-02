import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin(Plugins.Modules.SPRING)
}

tasks.getByName<Jar>("jar") {
    enabled = true
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

dependencies {
    implementation(projects.persistence.rdb)

    implementation(projects.common)
    implementation(projects.commonSpring)

    implementation(projects.infra.kakao)

    testImplementation(testFixtures(projects.persistence.rdb))
}
