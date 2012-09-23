package denoflionsx.Interfaces;

import denoflionsx.API.Interfaces.IPfFPlugin;

public interface IPluginRegistry {
    
    // New plugin core.
    
    public void registerLatePlugins();
    
    public void registerEarlyPlugins();
    
    public boolean isPluginAlive(String pluginName);
    
    public void addPlugin(IPfFPlugin plugin);
    
}
