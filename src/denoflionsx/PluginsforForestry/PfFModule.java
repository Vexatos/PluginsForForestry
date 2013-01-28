package denoflionsx.PluginsforForestry;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import denoflionsx.PluginsforForestry.Interfaces.IPfFModule;
import denoflionsx.denLib.denLib;

public abstract class PfFModule implements IPfFModule {

    @Override
    public void load(FMLInitializationEvent event) {
    }

    @Override
    public void modsLoaded(FMLPostInitializationEvent evt) {
    }

    @Override
    public void preLoad(FMLPreInitializationEvent event) {
    }

    @Override
    public abstract String targetMod();

    @Override
    public boolean canLoad() {
        if (denLib.LOADER.FML.isModLoaded(this.targetMod())){
            return true;
        }else{
            return false;
        }
    }

}
