package denoflionsx.API.Events;

import denoflionsx.API.Interfaces.IPfFPlugin;

public interface IListenerManager {
    
    public void register(IPluginListener listener);
    
    public void notifyListeners(IPfFPlugin l);
    
}
