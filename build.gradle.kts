plugins {
    kotlin("jvm") version "1.3.61"
    id("maven-publish")
}

object Settings {
    const val ARTIFACT_ID = "printf-parser"
    const val GROUP_ID = "com.github.bb441db"
    const val VERSION = "1.0"
    const val REPOSITORY_NAME = "PrintfParser"
    const val REPOSITORY_OWNER = "bb441db"
}

group = Settings.GROUP_ID
version = Settings.VERSION

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testApi("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit:1.3.61")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/${Settings.REPOSITORY_OWNER}/${Settings.REPOSITORY_NAME}")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = Settings.GROUP_ID
            artifactId = Settings.ARTIFACT_ID
            version = Settings.VERSION
            from(components["java"])
        }
    }
}