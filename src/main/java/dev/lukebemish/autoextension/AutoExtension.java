package dev.lukebemish.autoextension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark a class as a Groovy extension. This annotation processor generates the necessary files for the
 * class to be recognized as an extension: namely, a {@code META-INF/groovy/org.codehaus.groovy.runtime.ExtensionModule}
 * file.
 * <p>
 * This annotation processor takes two annotation processor properties, {@code autoextension.name} and
 * {@code autoextension.version}, which specify the {@code moduleName} and {@code moduleVersion} properties of the
 * generated file respectively.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AutoExtension {
    /**
     * @return Whether the extension class is static. If true, the extension will be added to the {@code staticExtensionClasses}
     * property instead of the {@code extensionClasses} property.
     */
    boolean isStatic() default false;
}
