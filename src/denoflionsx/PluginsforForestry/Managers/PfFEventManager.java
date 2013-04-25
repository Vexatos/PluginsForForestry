package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Annotations.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Events.IPfFEvent;
import denoflionsx.PluginsforForestry.API.Events.IPfFEventManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class PfFEventManager implements IPfFEventManager {

    private ArrayList<Object> listeners = new ArrayList();
    private ArrayList<Object> removeQueue = new ArrayList();
    
    @Override
    public void register(Object o) {
        listeners.add(o);
    }

    @Override
    public void unregister(Object o) {
        removeQueue.add(o);
    }

    @Override
    public void postEvent(IPfFEvent event) {
        for (Object o : listeners){
            for (Method m : o.getClass().getDeclaredMethods()){
                for (Annotation a : m.getDeclaredAnnotations()){
                    if (a instanceof PfFSubscribe){
                        for (Class c : m.getParameterTypes()){
                            if (c.equals(event.getClass())){
                                try{
                                    m.invoke(o, c.cast(event.getObject()));
                                }catch(Exception ex){
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        listeners.removeAll(removeQueue);
        listeners.trimToSize();
    }
}
