import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import tanvd.kosogor.proxy.publishJar

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.41")
    id("tanvd.kosogor") version "1.0.7" apply true
}

repositories {
    mavenLocal()
    jcenter()
}

group = "io.gitlab.arturbosch"
version = "1.0.1"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    api(platform("org.apache.maven.resolver:maven-resolver:1.4.0"))
    api("org.apache.maven.resolver:maven-resolver-api")
    api("org.apache.maven.resolver:maven-resolver-util")
    api("org.apache.maven.resolver:maven-resolver-impl")
    api("org.apache.maven.resolver:maven-resolver-spi")
    api("org.apache.maven.resolver:maven-resolver-transport-file")
    api("org.apache.maven.resolver:maven-resolver-transport-http")
    api("org.apache.maven.resolver:maven-resolver-connector-basic")
    api("org.apache.maven:maven-resolver-provider:3.6.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishJar {
    publication {
        artifactId = project.name
    }

    bintray {
        username = project.properties["bintrayUser"]?.toString() ?: System.getenv("BINTRAY_USER")
        secretKey = project.properties["bintrayApiKey"]?.toString() ?: System.getenv("BINTRAY_API_KEY")
        repository = "generic"
        info {
            githubRepo = "https://github.com/arturbosch/deps"
            vcsUrl = "https://github.com/arturbosch/deps"
            userOrg = "arturbosch"
            description = "Resolves transitive dependencies"
            license = "Apache-2.0"
        }
    }
}
