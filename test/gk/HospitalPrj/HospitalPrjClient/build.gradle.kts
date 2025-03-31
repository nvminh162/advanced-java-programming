plugins {
    id("java")
}

group = "edu.iuh.fit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(files("libs/HospitalPrj-1.0-SNAPSHOT.jar"))

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}