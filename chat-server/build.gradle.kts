import com.google.cloud.tools.jib.gradle.BaseImageParameters
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

	id("org.springframework.boot").version("2.4.0")
	id("io.spring.dependency-management").version("1.0.10.RELEASE")
	id("com.google.cloud.tools.jib").version("2.7.0")
	kotlin("jvm") version "1.4.20"
	kotlin("plugin.spring") version "1.4.20"
}

group = "io.mkrzywanski"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
	mavenCentral()
}

jib {
	from.image = "openjdk:14.0.2-jdk"
	to.image = project.name
}

dependencies {
	implementation("ch.qos.logback:logback-classic:1.2.3")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.9")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "14"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
