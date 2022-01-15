import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "kt"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

configurations.all {
    exclude(group = "ch.qos.logback")
    exclude(group="org.springframework.boot", module = "spring-boot-starter-logging")

    resolutionStrategy.eachDependency {
        if (requested.group == "org.apache.logging.log4j" && requested.name.startsWith("log4j")) {
            useVersion("2.17.0")
            because("fixes critical bug : CVE-2021-44228")
        }
    }
}


dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // redis
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.6.2")
    // spring-boot
    implementation("org.springframework.boot:spring-boot-starter:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:2.6.2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    // thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.6.2")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
//    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")
    // db
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.1")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
