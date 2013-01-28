package denoflionsx.PluginsforForestry.API.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Throw this up over a static int, boolean, or double field for easy config file access.
// Make sure you register with the manager. I like using a static body for it.

// Example:
// @Tunable(category = "core.items")
// public static int someID = 1234;
//
// static{
//  TunableManager.registerTunable(StaticClassName.class);
//}

// Run your mod and access that field. It will be set to whatever is in the config.

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Tunable {
    String category();
    String comment() default "Tunable Value";
}
