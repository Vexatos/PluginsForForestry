package denoflionsx.Annotations;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PfFAnnotationSearch {

    public static MethodAnnotation AnnotatedMethod(Class c, PfFEventTypes type) {
        for (Method m : c.getMethods()) {
            for (Annotation a : m.getAnnotations()) {
                if (a.annotationType().equals(PfFSubscribe.class)) {
                    PfFSubscribe p = (PfFSubscribe) a;
                    if (p.Event().equals(type)) {
                        return new MethodAnnotation(m, a);
                    }
                }
            }
        }
        return null;
    }

    public static class MethodAnnotation {

        private Method method;
        private Annotation annotation;

        public MethodAnnotation(Method method, Annotation annotation) {
            this.method = method;
            this.annotation = annotation;
        }

        public PfFSubscribe getAnnotation() {
            return (PfFSubscribe) annotation;
        }

        public Method getMethod() {
            return method;
        }
    }
}
