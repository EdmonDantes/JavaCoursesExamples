plugins {
    id("java")
}

group = "io.github.edmondantes" // 4
version = "0.0.1" // 5

repositories {
    mavenCentral()
}

dependencies {
    // 2
    implementation("org.apache.commons:commons-lang3:3.14.0")

    // 3
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}