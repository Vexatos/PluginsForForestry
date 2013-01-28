package denoflionsx.PluginsforForestry.Events;

import denoflionsx.PluginsforForestry.API.Events.IPfFEventManager;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class PfFEventManager implements IPfFEventManager {

    public static final ArrayList<Object> listeners = new ArrayList();
    private static final Class[] p = new Class[]{PfFEvent.class};

    @Override
    public void raiseAlert(String origin, String msg, Object obj) {
        PfFEvent event = new PfFEvent(origin, msg, obj);
        ArrayList<Object> lcopy = new ArrayList();
        for (Object o : listeners){
            lcopy.add(o);
        }
        for (Object o : lcopy) {
            if (o != null) {
                Class c = o.getClass();
                Method[] ms = c.getDeclaredMethods();
                for (Method m : ms) {
                    Annotation[] as = m.getDeclaredAnnotations();
                    for (Annotation a : as) {
                        if (a instanceof PfFSubscribe) {
                            if (Arrays.equals(m.getParameterTypes(), p)) {
                                try {
                                    m.invoke(o, event);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int registerListener(Object instance) {
        listeners.add(instance);
        return listeners.indexOf(instance);
    }

    @Override
    public boolean unregisterListener(int index) {
        return listeners.remove(this);
    }
}
