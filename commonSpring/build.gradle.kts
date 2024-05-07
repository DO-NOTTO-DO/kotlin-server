import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<Jar>("jar") {
    enabled = true
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

dependencies {
    implementation(projects.common)

    api(Dependencies.Spring.Boot.DATA_JPA)
}
