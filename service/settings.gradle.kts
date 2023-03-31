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
  }

}

rootProject.name = "service"

include(":official", ":prompt", ":common", ":mini")