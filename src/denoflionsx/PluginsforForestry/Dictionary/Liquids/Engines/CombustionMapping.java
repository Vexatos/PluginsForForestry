package denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines;

import buildcraft.api.fuels.IronEngineFuel;

public class CombustionMapping implements IEngineMapping {
    
    @Override
    public void MapEngineFuel(EngineFuel fuel) {
        IronEngineFuel.fuels.add(new IronEngineFuel(fuel.getLiquidStack().itemID, (float) fuel.getMJt(), fuel.getBurnTime()));
    }
}
