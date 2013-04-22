package denoflionsx.PluginsforForestry.Plugins.MFR;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Interface.IPfFPlugin;
import denoflionsx.PluginsforForestry.Plugins.MFR.Straw.PfFStrawHandler;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;

public class PluginMFR implements IPfFPlugin {

    @Override
    public void onPreLoad() {
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
        FarmingRegistry.registerLiquidDrinkHandler(PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID), new PfFStrawHandler());
    }
}
