plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
    alias(libs.plugins.blossom)
}

group = "xyz.thehiddenobject"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://repo.okaeri.cloud/releases")
    maven("https://repo.panda-lang.org/releases")
    maven("https://repo.eternalcode.pl/releases")
}

dependencies {
    compileOnly(libs.paper.api)
    implementation(libs.kotlin.stdlib)
    implementation(libs.guice)
    implementation(libs.okaeri.configs.yaml.bukkit)
    implementation(libs.okaeri.configs.serdes.bukkit)
    implementation(libs.multification)
    implementation(libs.multification.okaeri)
    implementation(libs.litecommands)
}

kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        blossom {
            kotlinSources {
                property("version", project.version.toString())
            }
        }
    }
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
