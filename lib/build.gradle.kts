plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
            version = "v0.3" // Update with your version tag

            from(components["java"])
        }
    }
}
