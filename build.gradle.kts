plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
}

group = "xyz.thehiddenobject"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://repo.okaeri.cloud/releases")
}

dependencies {
    compileOnly(libs.paper.api)
    implementation(libs.kotlin.stdlib)
    implementation(libs.guice)
    implementation(libs.okaeri.configs)
    implementation(libs.okaeri.configs.yaml.bukkit)
}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
