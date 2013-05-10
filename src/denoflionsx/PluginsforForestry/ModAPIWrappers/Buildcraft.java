package denoflionsx.PluginsforForestry.ModAPIWrappers;

import buildcraft.api.fuels.IronEngineFuel;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;

public class Buildcraft {

    public static void engine(EngineFuel fuel) {
        try {
            IronEngineFuel.fuels.add(new IronEngineFuel(fuel.getLiquidStack().itemID, (float) fuel.getMJt(), fuel.getBurnTime()));
        } catch (NoClassDefFoundError ex) {
        }
    }
}
