plugins {
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

gradlePlugin {
    plugins {
        create("torgraysDatagen") {
            id = "com.ususstudios.torgrays_datagen"
            implementationClass = "com.ususstudios.torgrays_datagen.TorgraysDatagenPlugin"
        }
    }
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
            version = "v0.1" // Update with your version tag

            from(components["java"])
        }
    }
}
