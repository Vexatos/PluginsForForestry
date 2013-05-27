package denoflionsx.PluginsforForestry.Plugins.Railcraft;

import cpw.mods.fml.common.Loader;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.Railcraft.Items.ItemCustomCoke;
import net.minecraft.item.Item;

public class PluginRailcraft implements IPfFPlugin {
    
    public static Item itemCharCoke;
    
    @Override
    public void onPreLoad() {
        if (Loader.isModLoaded("Railcraft")) {
        }
    }
    
    @Override
    public void onLoad() {
        if (Loader.isModLoaded("Railcraft")) {
            if (PfFTuning.getInt(PfFTuning.Railcraft.plugin_railcraft_charcoal) > 0) {
                itemCharCoke = new ItemCustomCoke(PfFTuning.getInt(PfFTuning.Railcraft.plugin_railcraft_charcoal));
                PfF.Proxy.ItemCollections.add(this.getClass());
            }
        }
    }
    
    @Override
    public void onPostLoad() {
        if (Loader.isModLoaded("Railcraft")) {
        }
    }
}
