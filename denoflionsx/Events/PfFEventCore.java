package denoflionsx.Events;

import denoflionsx.API.PfFEvents;

public class PfFEventCore {
    
    public void init(){
        PfFEvents.pluginLoading = new PfFPluginLoadingManager();
        PfFEvents.pluginLoaded = new PfFPluginLoadedManager();
    }
}
