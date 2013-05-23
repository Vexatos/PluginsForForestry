package denoflionsx.PluginsforForestry.Managers;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPluginManager;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import java.util.ArrayList;
import java.util.Collections;

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
        if (!(this.getPluginsList().get(0) instanceof PluginLR)) {
            int index = 0;
            for (IPfFPlugin plugin : this.getPluginsList()) {
                if (plugin instanceof PluginLR) {
                    index = this.getPluginsList().indexOf(plugin);
                    break;
                }
            }
            if (index != 0) {
                Collections.swap(plugins, index, 0);
                PfF.Proxy.print("Moved Liquid Roundup to top priority load slot.");
            }
        }
        for (IPfFPlugin plugin : this.getPluginsList()) {
            if (o instanceof FMLPreInitializationEvent) {
                plugin.onPreLoad();
            } else if (o instanceof FMLInitializationEvent) {
                plugin.onLoad();
            } else if (o instanceof FMLPostInitializationEvent) {
                plugin.onPostLoad();
            }
        }
    }
}
