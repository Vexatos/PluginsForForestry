package denoflionsx.Events;

import denoflionsx.API.Events.EventModuleLoaded;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Events.IModuleListener;
import denoflionsx.API.Interfaces.IPfFModule;
import java.util.ArrayList;

public class PfFModuleLoadedManager implements IListenerManager{
    
    public static ArrayList<IModuleListener> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        IPfFModule m = (IPfFModule)l;
        for (IModuleListener a : listeners){
            a.moduleLoaded(new EventModuleLoaded(m));
        }
    }

    @Override
    public void register(Object listener) {
        IModuleListener i = (IModuleListener)listener;
        listeners.add(i);
    }

}
