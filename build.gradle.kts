plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.41")
}

repositories {
    jcenter()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation(platform("org.apache.maven.resolver:maven-resolver:1.4.0"))
    implementation("org.apache.maven.resolver:maven-resolver-util")
    implementation("org.apache.maven.resolver:maven-resolver-impl")
    implementation("org.apache.maven.resolver:maven-resolver-spi")
    implementation("org.apache.maven.resolver:maven-resolver-transport-file")
    implementation("org.apache.maven.resolver:maven-resolver-transport-http")
    implementation("org.apache.maven.resolver:maven-resolver-connector-basic")
    implementation("org.apache.maven:maven-resolver-provider:3.6.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
