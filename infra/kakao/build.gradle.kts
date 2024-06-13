tasks.getByName<Jar>("jar") {
    enabled = true
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

dependencies {
    implementation(projects.common)
    implementation(projects.commonSpring)

    implementation(projects.persistence.rdb)
    
    implementation(Dependencies.Spring.Cloud.OPEN_FEIGN)
}
