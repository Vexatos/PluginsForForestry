package denoflionsx.Events;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Events.IPluginListener;
import denoflionsx.core.IPfFPluginTemplate;
import java.util.ArrayList;

public class PfFPluginLoadedManager implements IListenerManager {

    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        IPfFPluginTemplate z = (IPfFPluginTemplate) l;
        for (Object a : listeners) {
            IPluginListener p = (IPluginListener) a;
            p.pluginLoaded(new EventPluginLoaded(this, z));
        }
    }

    @Override
    public void register(Object listener) {
        listeners.add(listener);
    }
}
