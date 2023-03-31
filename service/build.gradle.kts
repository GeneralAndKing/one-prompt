plugins {
  java
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("org.graalvm.buildtools.native")
}


extra["testcontainersVersion"] = "1.17.6"

allprojects {
  repositories {
    maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
    maven { url = uri("https://repo.spring.io/milestone") }
    mavenCentral()
  }

}

subprojects {
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")
  apply(plugin = "org.graalvm.buildtools.native")
  apply(plugin = "java")

  java.sourceCompatibility = JavaVersion.VERSION_17
  java.targetCompatibility = JavaVersion.VERSION_17

  group = "one.prompt"
  version = "0.0.1-SNAPSHOT"

  configurations {
    compileOnly {
      extendsFrom(configurations.annotationProcessor.get())
    }
  }
  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-data-redis")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//	implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(group = "com.querydsl", name = "querydsl-jpa", classifier = "jakarta")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor(
      group = "com.querydsl", name = "querydsl-apt", classifier = "jakarta"
    )
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
  }

  dependencyManagement {
    imports {
      mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
    }
  }

  tasks.test {
    useJUnitPlatform()
  }

}
