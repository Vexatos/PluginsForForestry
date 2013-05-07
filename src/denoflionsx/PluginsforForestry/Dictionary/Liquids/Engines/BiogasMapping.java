package denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines;

import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;

public class BiogasMapping implements IEngineMapping {

    private final int safety = 1;

    @Override
    public void MapEngineFuel(EngineFuel fuel) {
        if (EngineFuel.readFromConfig(fuel, "biogas")) {
            FuelManager.bronzeEngineFuel.put(fuel.getLiquidStack().asItemStack(), new EngineBronzeFuel(fuel.getLiquidStack().asItemStack(), fuel.getMJt(), fuel.getBurnTime(), safety));
        }
    }
}
