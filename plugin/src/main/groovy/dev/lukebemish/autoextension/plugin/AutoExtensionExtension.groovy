package dev.lukebemish.autoextension.plugin

import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.provider.Property

@CompileStatic
abstract class AutoExtensionExtension {
    static final String NAME = "autoExtension"

    /**
     * Whether to automatically configure the annotation processor.
     */
    abstract Property<Boolean> getAutomaticConfiguration()
    /**
     * The name of the groovy extension module to generate a file for. Defaults to the project name.
     */
    abstract Property<String> getName()
    /**
     * The version of the groovy extensin module to generate a file for. Defaults to the project version.
     */
    abstract Property<String> getLibraryVersion()

    protected final Project project

    AutoExtensionExtension(final Project project) {
        this.project = project
        automaticConfiguration.set(true)
        name.set(project.name)
    }
}
