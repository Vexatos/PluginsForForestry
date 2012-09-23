package denoflionsx.Events;

import denoflionsx.API.Events.EventPluginLoading;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Events.IPluginListener;
import denoflionsx.API.Interfaces.IPfFPlugin;
import java.util.ArrayList;

public class PfFPluginLoadingManager implements IListenerManager{
    
    public static ArrayList<IPluginListener> listeners = new ArrayList();

    @Override
    public void notifyListeners(IPfFPlugin l) {
        for (IPluginListener o : listeners){
            o.pluginLoading(new EventPluginLoading(this,l));
        }
    }

    @Override
    public void register(IPluginListener listener) {
        listeners.add(listener);
    }
    
}
