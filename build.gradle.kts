plugins {
    kotlin("jvm") version "1.5.10"
    id("me.champeau.jmh") version "0.6.6"
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jmh {
    warmupForks.set(0)
    warmupIterations.set(3)
    fork.set(1)
//    profilers.set(listOf("async:libPath=/Users/Sergey.Rostov/Downloads/async-profiler-2.5-macos/build/libasyncProfiler.dylib"))
    jvmArgsPrepend.addAll(
        "-XX:+UnlockDiagnosticVMOptions",
//        "-XX:+PrintCompilation",
//        "-XX:+LogCompilation",
        "-XX:+PrintAssembly",
//        "-XX:CompileCommand=print,justdoit.jmh_generated.*",
        "-XX:LogFile=log.txt"
    )
}