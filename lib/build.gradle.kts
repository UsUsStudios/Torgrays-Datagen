plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20250107")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

publishing {
    publications {
        create<MavenPublication>("jitpack") {
            groupId = "com.github.UsUsStudios"
            artifactId = "Torgrays-Datagen"
            version = "v0.7" // Update with your version tag

            from(components["java"])
        }
    }
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java"))
        }
    }
}

tasks.jar {
    from(sourceSets.main.get().output) // Ensures compiled classes are included
}
