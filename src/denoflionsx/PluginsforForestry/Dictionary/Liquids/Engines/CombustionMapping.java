package denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines;

import denoflionsx.PluginsforForestry.ModAPIWrappers.Buildcraft;

public class CombustionMapping implements IEngineMapping {
    
    @Override
    public void MapEngineFuel(EngineFuel fuel) {
        Buildcraft.engine(fuel);
    }
}
