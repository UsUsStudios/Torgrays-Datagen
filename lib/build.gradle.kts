plugins {
    `java-gradle-plugin`
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