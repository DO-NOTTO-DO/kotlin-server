object Dependencies {
    object Spring {
        object Boot {
            const val WEB = "org.springframework.boot:spring-boot-starter-web"
            const val DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
            const val VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
            const val CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"
            const val SECURITY = "org.springframework.boot:spring-boot-starter-security"
            const val JAKARTA_XML = "jakarta.xml.bind:jakarta.xml.bind-api"
        }

        object Batch {
            const val BATCH = "org.springframework.boot:spring-boot-starter-batch"
        }

        object Rest {
            const val REST_ASSURED = "io.rest-assured:spring-mock-mvc"
        }

        object RestDocs {
            const val MOCK_MVC = "org.springframework.restdocs:spring-restdocs-mockmvc"
            const val ASCII_DOCTOR = "org.springframework.restdocs:spring-restdocs-asciidoctor"
        }
    }

    object Test {
        const val KOTEST_JUNIT5 = "io.kotest:kotest-runner-junit5-jvm:${Versions.KOTEST}"
        const val KOTEST_CORE = "io.kotest:kotest-assertions-core:${Versions.KOTEST}"
        const val KOTEST_SPRING = "io.kotest.extensions:kotest-extensions-spring:${Versions.KOTEST_SPRING}"
        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val TEST = "org.springframework.boot:spring-boot-starter-test"
    }

    object Kotlin {
        const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
        const val STDLIB_JDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    }

    object Jackson {
        const val MODULE_KOTLIN = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}"
        const val DATATYPE_JSR310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.JACKSON}"
    }

    object JWT {
        const val JWT_API = "io.jsonwebtoken:jjwt-api:${Versions.JWT}"
        const val JWT_IMPL = "io.jsonwebtoken:jjwt-impl:${Versions.JWT}"
        const val JWT_JACKSON = "io.jsonwebtoken:jjwt-jackson:${Versions.JWT}"
    }

    object Database {
        const val H2 = "com.h2database:h2"
        const val MYSQLDB = "mysql:mysql-connector-java:${Versions.MYSQL}"
    }

    object Querydsl {
        const val JPA = "com.querydsl:querydsl-jpa:${Versions.QUERYDSL}:jakarta"
        const val APT = "com.querydsl:querydsl-apt:${Versions.QUERYDSL}:jakarta"
    }

    object Firebase {
        const val FIREBASE_ADMIN = "com.google.firebase:firebase-admin:${Versions.FIREBASE_ADMIN}"
    }

    object Google {
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    }
}
