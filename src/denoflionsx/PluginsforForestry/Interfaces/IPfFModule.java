package denoflionsx.PluginsforForestry.Interfaces;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IPfFModule {
    
    public void preLoad(FMLPreInitializationEvent event);
    
    public void load(FMLInitializationEvent event);
    
    public void modsLoaded(FMLPostInitializationEvent evt);
    
    public String targetMod();
    
    public boolean canLoad();

}
