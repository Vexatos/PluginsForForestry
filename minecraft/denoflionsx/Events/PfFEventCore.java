package denoflionsx.Events;

import denoflionsx.API.PfFEvents;

public class PfFEventCore {
    
    public PfFEventCore init(){
        PfFEvents.pluginLoaded = new PfFPluginLoadedManager();
        PfFEvents.moduleLoaded = new PfFModuleLoadedManager();
        PfFEvents.itemInitialized = new PfFItemInitializedManager();
        PfFEvents.specialEvent = new PfFSpecialEventManager();
        return this;
    }
}
