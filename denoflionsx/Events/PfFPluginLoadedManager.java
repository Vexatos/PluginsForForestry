package denoflionsx.Events;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Events.IPluginListener;
import denoflionsx.Annotations.PfFAnnotationSearch;
import denoflionsx.Annotations.PfFAnnotationSearch.MethodAnnotation;
import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Interfaces.IPfFPlugin;
import java.lang.Object;
import java.util.ArrayList;

public class PfFPluginLoadedManager implements IListenerManager {

    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        IPfFPlugin z = (IPfFPlugin) l;
        for (Object a : listeners) {
            if (a instanceof IPluginListener) {
                IPluginListener q = (IPluginListener) a;
                q.pluginLoaded(new EventPluginLoaded(this, z));
            }
            MethodAnnotation m = PfFAnnotationSearch.AnnotatedMethod(a.getClass(),PfFEventTypes.PLUGIN_LOADED);
            if (m != null) {
                try {
                    m.getMethod().invoke(a, new EventPluginLoaded(this, z));
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
