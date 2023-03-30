import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.graalvm.buildtools.native") version "0.9.20"
  id("org.asciidoctor.jvm.convert") version "3.3.2"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "one.prompt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
  maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
  maven { url = uri("https://repo.spring.io/milestone") }
  mavenCentral()
}

val asciidoctorExtensions: Configuration by configurations.creating
val snippetsDir by extra { file("build/generated-snippets") }
extra["snippetsDir"] = file("build/generated-snippets")
extra["testcontainersVersion"] = "1.17.6"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-data-redis")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
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

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.test {
  doFirst { delete(snippetsDir) }
  outputs.dir(snippetsDir)
  useJUnitPlatform()
}

// https://github.com/spring-io/start.spring.io/issues/676#issuecomment-859641317
tasks.asciidoctor {
  doFirst {
    delete(outputDir)
    copy {
      from(snippetsDir)
      into(sourceDir)
    }
  }
  dependsOn(tasks.test)
  configurations(asciidoctorExtensions.name)
  setOutputDir(file("src/main/resources/static/docs"))
  attributes(
    mapOf(
      "snippets" to snippetsDir,
      "source-highlighter" to "highlight.js"
    )
  )
}

tasks.bootJar {
  dependsOn(tasks.asciidoctor)
  dependsOn(tasks.withType<Copy>())
}