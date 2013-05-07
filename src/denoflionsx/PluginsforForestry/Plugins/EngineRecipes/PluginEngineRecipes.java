package denoflionsx.PluginsforForestry.Plugins.EngineRecipes;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.PfFEngineParser;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;

public class PluginEngineRecipes implements IPfFPlugin, IdenWorldEventHandler {

    @Override
    public void onPreLoad() {
        PfFEngineParser.createInstance();
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
        WorldEventHandler.registerHandler(this);
    }

    /*
     * Liquids created from Forestry IPlugin classes (basically everything in
     * forestry and ExtraBees) initalize in post. So I need to wait even longer.
     */
    @Override
    public void onWorldLoaded() {
        PfFEngineParser.instance.parse();
        WorldEventHandler.unregisterHandler(this);
    }

    @Override
    public void onWorldEnded() {
    }
}
