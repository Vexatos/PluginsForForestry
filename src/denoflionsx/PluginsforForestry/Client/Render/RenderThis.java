package denoflionsx.PluginsforForestry.Client.Render;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RenderThis {
    
    String renderFile() default "bucket.txt";
}
