package denoflionsx.PluginsforForestry.Managers;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPluginManager;
import java.util.ArrayList;

public class PfFPluginManager implements IPfFPluginManager {

    private ArrayList<IPfFPlugin> plugins = new ArrayList();

    @Override
    public ArrayList<IPfFPlugin> getPluginsList() {
        return plugins;
    }

    @Override
    public void registerPlugin(IPfFPlugin plugin) {
        plugins.add(plugin);
    }

    @Override
    public void runPluginLoadEvent(Object o) {
        for (IPfFPlugin plugin : this.getPluginsList()){
            if (o instanceof FMLPreInitializationEvent){
                plugin.onPreLoad();
            }else if (o instanceof FMLInitializationEvent){
                plugin.onLoad();
            }else if (o instanceof FMLPostInitializationEvent){
                plugin.onPostLoad();
            }
        }
    }
}
