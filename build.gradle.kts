import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// 남이 잘 만들어둔 Gradle task나 각종 확장들을 가져오는 역할
// https://plugins.gradle.org/
plugins {
    // 스프링 실행, 빌드를 위한 각종 Gradle task 등을 제공 (bootRun, build 등)
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/
    // https://docs.spring.io/spring-boot/docs/2.7.1/gradle-plugin/api/
    id("org.springframework.boot") version "2.7.1"
    // https://github.com/spring-gradle-plugins/dependency-management-plugin
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.3.0"

    val kotlinVersion = "1.6.21"
    kotlin("jvm") version "1.6.21"	// Kotlin 으로 작성된 프로젝트의 컴파일 및 다른 task 를 위한 플러그인
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21" // // Entity NoArgsConstructor 어노테이션이 없어도 되게 해줌
    kotlin("kapt") version kotlinVersion
}

val javaVersion = JavaVersion.VERSION_11

java.sourceCompatibility = javaVersion

// dependencies 를 다운받을 저장소
// private package(클린 아키텍쳐에서 컴포넌트라고 논했던 것)의 경우에도 여기에 추가해서 다운받을 수 있음
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("junit:junit:4.13.1")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    bootRun {
        dependsOn(ktlintFormat)
        args("--spring.profiles.active=local")
    }

    // KotlinCompile 스텝을 사용자화
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion.majorVersion
        }
    }

    // Test 스텝을 사용자화
    withType<Test> {
        useJUnitPlatform()
    }

    withType<org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask> {
        workerMaxHeapSize.set("1024m")
    }
}
