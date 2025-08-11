plugins {
    checkstyle
    java

    alias(libs.plugins.axion)
    alias(libs.plugins.lombok)
    alias(libs.plugins.shadow)
}

group = "tech.ixirsii.map"
version = scmVersion.version

repositories {
    mavenCentral()
    // Spigot
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot.api)

    implementation(libs.bundles.grizzly)
    implementation(libs.bundles.jackson)
    implementation(libs.bundles.jakarta)
    implementation(libs.bundles.jersey)
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.12.1")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

checkstyle {
    toolVersion = "10.23.0"
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        html.required = true
        xml.required = false
    }
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "tech.ixirsii.map.MapPlugin"
    }

//    relocate("javax.ws.rs.core", "tech.ixirsii.map.javax.ws.rs.core")
}
