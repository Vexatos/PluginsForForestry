package denoflionsx.Beta;

import denoflionsx.core.core;
import java.lang.annotation.Annotation;

@TestAnnotation(dude = "Totally")
public class AnnotationsTest {

    public static void CheckAnnotations() {
        try {
            Class c = Class.forName("denoflionsx.Beta.AnnotationsTest");
            Annotation a = c.getAnnotation(TestAnnotation.class);
            TestAnnotation t = (TestAnnotation)a;
            core.print(t.dude());
        } catch (Exception ex) {
        }
    }
}
