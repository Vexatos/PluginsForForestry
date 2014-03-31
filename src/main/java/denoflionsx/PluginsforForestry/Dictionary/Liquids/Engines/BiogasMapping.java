package denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines;

import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;

public class BiogasMapping implements IEngineMapping {

    private final int safety = 1;

    @Override
    public void MapEngineFuel(EngineFuel fuel) {
        if (EngineFuel.readFromConfig(fuel, "biogas")) {
            Forestry.biogas(fuel, safety);
        }
    }
}
