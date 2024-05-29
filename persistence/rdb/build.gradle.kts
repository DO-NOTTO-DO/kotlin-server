import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id(Plugins.Id.SPRING_BOOT)

    kotlin(Plugins.Modules.SPRING)
    kotlin(Plugins.Modules.JPA)
    kotlin(Plugins.Modules.KAPT)
    id(Plugins.Modules.KOTLIN_KAPT)
}


tasks.getByName<Jar>("jar") {
    enabled = true
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

allOpen {
    annotation("nottodo.persistence.rdb.config.annotation.AllOpen")
    annotation("jakarta.persistence.Entity")
}

val copyYml = tasks.register<Copy>("copyYml") {
    from("../../server-secret/application-secret-rdb.yml")
    into("src/main/resources/")
}

tasks.processResources {
    dependsOn(copyYml)
}

dependencies {
    implementation(projects.common)
    implementation(projects.commonSpring)

    implementation(Dependencies.Jackson.DATATYPE_JSR310)
    implementation(Dependencies.Jackson.MODULE_KOTLIN)
//    implementation(Dependencies.Spring.Boot.SECURITY)

    api(Dependencies.Spring.Boot.DATA_JPA)
    api(Dependencies.Querydsl.JPA)
    kapt(Dependencies.Querydsl.APT)

    runtimeOnly(Dependencies.Database.H2)
    runtimeOnly(Dependencies.Database.MYSQLDB)
}
