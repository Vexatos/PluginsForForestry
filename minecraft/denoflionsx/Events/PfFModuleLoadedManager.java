package denoflionsx.Events;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Events.*;
import denoflionsx.API.Interfaces.IPfFModule;
import denoflionsx.Annotations.PfFAnnotationSearch;
import java.util.ArrayList;

public class PfFModuleLoadedManager implements IListenerManager {

    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        IPfFModule z = (IPfFModule) l;
        for (Object a : listeners) {
            if (a instanceof IModuleListener) {
                IModuleListener q = (IModuleListener) a;
                q.moduleLoaded(new EventModuleLoaded(this, z));
            }
            PfFAnnotationSearch.MethodAnnotation m = PfFAnnotationSearch.AnnotatedMethod(a.getClass(),PfFEventTypes.MODULE_LOADED);
            if (m != null) {
                try {
                    m.getMethod().invoke(a, new EventModuleLoaded(this, z));
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
