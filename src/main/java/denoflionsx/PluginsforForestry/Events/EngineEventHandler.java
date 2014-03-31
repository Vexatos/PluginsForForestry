package denoflionsx.PluginsforForestry.Events;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EngineEventHandler {

    public static EngineEventHandler instance;
    private ArrayList<Object> listeners = new ArrayList();
    private HashMap<Object, Method> methods = new HashMap();

    public static void create() {
        instance = new EngineEventHandler();
    }

    public void register(Object instance) {
        Class c = instance.getClass();
        for (Method m : c.getDeclaredMethods()) {
            for (Annotation a : m.getDeclaredAnnotations()) {
                if (a instanceof PfFSubscribe) {
                    if (Arrays.deepEquals(m.getParameterTypes(), new Class[]{EngineEvent.class})) {
                        methods.put(instance, m);
                        listeners.add(instance);
                    }
                }
            }
        }
    }

    public void runEvent(EngineEvent e) {
        for (Object o : listeners) {
            try {
                methods.get(o).invoke(o, e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
