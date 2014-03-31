package denoflionsx.PluginsforForestry.ModAPIWrappers;

import buildcraft.api.fuels.IronEngineFuel;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;

public class Buildcraft {

    public static void engine(EngineFuel fuel) {
        try {
            IronEngineFuel.addFuel(fuel.getFluid(), fuel.getMJt(), fuel.getBurnTime());
        } catch (NoClassDefFoundError ex) {
        }
    }
}
