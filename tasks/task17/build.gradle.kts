plugins {
    id("java")
}

group = "io.github.edmondantes"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.32")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.0")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation("org.projectlombok:lombok:1.18.32")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.test {
    useJUnitPlatform()
}