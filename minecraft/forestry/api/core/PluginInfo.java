package forestry.api.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PluginInfo
{
    String pluginID();

    String name();

String author() default "";

String url() default "";

String version() default "";

String description() default "";

String help() default "";
}
