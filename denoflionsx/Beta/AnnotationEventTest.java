package denoflionsx.Beta;

import denoflionsx.API.Annotations.PfFSubscribe;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AnnotationEventTest {

    public static ArrayList<Object> listeners = new ArrayList();

    public static void register(Object c) {
        listeners.add(c);
    }

    public static void notify(AnnotationsEvent event) {
        for (Object o : listeners) {
            Class c = o.getClass();
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType().equals(PfFSubscribe.class)) {
                        try {
                            method.invoke(o, event.getTest());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}
