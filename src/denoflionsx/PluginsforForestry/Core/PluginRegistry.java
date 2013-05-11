package denoflionsx.PluginsforForestry.Core;

import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.PluginBarrel;
import denoflionsx.PluginsforForestry.Plugins.EngineRecipes.PluginEngineRecipes;
import denoflionsx.PluginsforForestry.Plugins.LiquidRecipes.PluginLiquidRecipes;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Plugins.OmniPlant.PluginOmniPlant;

public class PluginRegistry {

    public static void register() {
        PfFAPI.plugins.registerPlugin(new PluginLR());
        PfFAPI.plugins.registerPlugin(new PluginLiquidRecipes());
        PfFAPI.plugins.registerPlugin(new PluginOmniPlant());
        PfFAPI.plugins.registerPlugin(new PluginEngineRecipes());
        PfFAPI.plugins.registerPlugin(new PluginBarrel());
    }
}