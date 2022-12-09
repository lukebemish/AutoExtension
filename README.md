# AutoExtension

[![Maven Central](https://img.shields.io/maven-central/v/dev.lukebemish.autoextension/autoextension?style=for-the-badge)](https://search.maven.org/artifact/dev.lukebemish.autoextension/autoextension)

AutoExtension is an annotation processor for generating the proper `META-INF` files for groovy to locate extension classes. It can generate this file
automatically and include all classes marked with the extension.

### Use

To use, first add the `autoextension` artifact, which is on maven central, as a compile-time dependency and annotation processor. Using gradle, that would
look like this:
```gradle
repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor 'dev.lukebemish.autoextension:autoextension:<version>'
    compileOnly 'dev.lukebemish.autoextension:autoextension:<version>'
}
```
To use it, simple annotate extension classes with the `@AutoExtension` annotation, which takes an optional `isStatic` argument to mark classes as static
extensions. You will also likely need to specify the extension module version and name. This is done through compiler arguments; in gradle, it would look
like:
```gradle
tasks.compileJava {
    options.compilerArgs += ['-Aautoextension.name=Extension Module Name', "-Aautoextension.version=${version}"]
}
```
