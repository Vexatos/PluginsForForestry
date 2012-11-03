package denoflionsx.Events;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Events.EventFuelCreated;
import denoflionsx.API.Events.EventFuelCreatedObject;
import denoflionsx.API.Events.IFuelListener;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.Annotations.PfFAnnotationSearch;
import java.util.ArrayList;

public class PfFFuelCreatedManager implements IListenerManager{
    
    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        EventFuelCreatedObject liquid = (EventFuelCreatedObject)l;
        for (Object o : listeners){
            if (o instanceof IFuelListener){
                IFuelListener fl = (IFuelListener)o;
                fl.onFuelCreation(new EventFuelCreated(this,liquid));
            }
            PfFAnnotationSearch.MethodAnnotation m = PfFAnnotationSearch.AnnotatedMethod(o.getClass(), PfFEventTypes.FUEL);
            if (m != null) {
                try {
                    m.getMethod().invoke(o, new EventFuelCreated(this,liquid));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void register(Object listener) {
        listeners.add(listener);
    }

}
