package denoflionsx.PluginsforForestry.Annotations;

import java.lang.annotation.Annotation;

public class UpdaterParser {

    public static String getVersion(Class c) {
        Annotation[] annotations = c.getDeclaredAnnotations();
        for (Annotation a : annotations) {
            if (a instanceof Updater) {
                Updater u = (Updater) a;
                return u.version();
            }
        }
        return "Unknown";
    }

    public static String getTag(Class c) {
        Annotation[] annotations = c.getDeclaredAnnotations();
        for (Annotation a : annotations) {
            if (a instanceof Updater) {
                Updater u = (Updater) a;
                return u.tag();
            }
        }
        return "Unknown";
    }

    public static String getURL(Class c) {
        Annotation[] annotations = c.getDeclaredAnnotations();
        for (Annotation a : annotations) {
            if (a instanceof Updater) {
                Updater u = (Updater) a;
                return u.url();
            }
        }
        return "Unknown";
    }
}
