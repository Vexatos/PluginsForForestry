package denoflionsx.API;

import denoflionsx.API.Events.IListenerManager;

public class PfFEvents {
    
    // Implement IPluginListener and register with what you want to know.
    // Alternatively, see PfFSubscribe annotation.
    
    public static IListenerManager pluginLoaded;
    
    public static IListenerManager moduleLoaded;
    
    public static IListenerManager itemInitialized;
    
    public static IListenerManager specialEvent;
    
}
