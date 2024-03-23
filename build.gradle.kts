plugins {
  id("java")
}

group = "openprotocol"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.9.1"))
  testImplementation("org.junit.jupiter:junit-jupiter")
  compileOnly("com.google.code.gson:gson:2.9.1")
}

tasks.test {
  useJUnitPlatform()
}