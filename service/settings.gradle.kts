pluginManagement {
  repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
    gradlePluginPortal()
    google()
  }

  plugins {
    id("org.springframework.boot") version extra["spring.boot.version"] as String
    id("io.spring.dependency-management") version extra["spring.dependency.version"] as String
    id("org.graalvm.buildtools.native") version extra["native.version"] as String
    id("org.asciidoctor.jvm.convert") version extra["asciidoctor.version"] as String
    kotlin("jvm") version extra["kotlin.version"] as String
    kotlin("plugin.spring") version extra["kotlin.version"] as String
    kotlin("plugin.jpa") version extra["kotlin.version"] as String
  }

}

rootProject.name = "service"

include(":official", ":prompt", ":common")