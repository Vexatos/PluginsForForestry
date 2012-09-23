package denoflionsx.Events;

import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.API.Events.IPluginListener;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Interfaces.IPfFPlugin;
import java.util.ArrayList;

public class PfFPluginLoadedManager implements IListenerManager{
    
    public static ArrayList<IPluginListener> listeners = new ArrayList();

    @Override
    public void notifyListeners(IPfFPlugin l) {
        for (IPluginListener a : listeners){
            a.pluginLoaded(new EventPluginLoaded(this,l));
        }
    }

    @Override
    public void register(IPluginListener listener) {
        listeners.add(listener);
    }

}
