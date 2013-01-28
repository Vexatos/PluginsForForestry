package denoflionsx.PluginsforForestry.API.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DepricatedTunable {
    String category();
    String comment() default "Tunable Value";
}
