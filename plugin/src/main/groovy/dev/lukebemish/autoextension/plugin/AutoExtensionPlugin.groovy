package dev.lukebemish.autoextension.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.GroovyCompile
import org.gradle.api.tasks.compile.JavaCompile

class AutoExtensionPlugin implements Plugin<Project> {
    public static final String CONFIGURATION_NAME = 'autoExtension'

    @Override
    void apply(Project project) {
        Configuration configuration = project.configurations.create(CONFIGURATION_NAME)

        final ext = project.extensions.create(AutoExtensionExtension.NAME, AutoExtensionExtension)
        project.afterEvaluate {
            if (ext.automaticConfiguration.get()) {
                project.tasks.withType(GroovyCompile).configureEach {
                    it.options.compilerArgs += ["-Aautoextension.name=${ext.name.get()}", "-Aautoextension.version=${project.version}"]
                }
                project.tasks.withType(JavaCompile).configureEach {
                    it.options.compilerArgs += ["-Aautoextension.name=${ext.name.get()}", "-Aautoextension.version=${project.version}"]
                }
                configuration.dependencies.add(project.dependencies.create("dev.lukebemish.autoextension:autoextension:${ext.libraryVersion.get()}"))
                final srcSets = project.extensions.getByType(JavaPluginExtension).sourceSets
                srcSets.each { srcSet ->
                    project.configurations.getByName(srcSet.compileClasspathConfigurationName).extendsFrom(configuration)
                    project.configurations.getByName(srcSet.annotationProcessorConfigurationName).extendsFrom(configuration)
                }
            }
        }
    }
}
