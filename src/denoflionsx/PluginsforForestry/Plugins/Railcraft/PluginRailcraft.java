package denoflionsx.PluginsforForestry.Plugins.Railcraft;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.Railcraft.Items.ItemCustomCoke;
import denoflionsx.PluginsforForestry.Plugins.Railcraft.Items.RCItems;

public class PluginRailcraft implements IPfFPlugin {
    
    @Override
    public void onPreLoad() {
    }
    
    @Override
    public void onLoad() {
        RCItems.itemCharCoke = new ItemCustomCoke(PfFTuning.getInt(PfFTuning.Railcraft.plugin_railcraft_charcoal));
        PfF.Proxy.ItemCollections.add(RCItems.class);
    }
    
    @Override
    public void onPostLoad() {
    }
}
