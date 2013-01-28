package denoflionsx.LiquidRoundup.APIWrappers.Railcraft;

import denoflionsx.LiquidRoundup.API.Annotations.Wrapper;
import denoflionsx.LiquidRoundup.Interfaces.IAPIWrapper;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel.EngineFuel;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import railcraft.common.api.crafting.RailcraftCraftingManager;
import railcraft.common.api.fuel.FuelManager;

@Wrapper
public class Railcraft implements IAPIWrapper {

    public CokeOvenManager cokeOven = new CokeOvenManager();
    public BoilerManager boiler = new BoilerManager();
    private boolean hasWarned = false;

    @Override
    public void warning() {
        if (!hasWarned) {
            LiquidRoundup.Proxy.print("Railcraft API not found!");
            hasWarned = true;
        }
    }

    public class BoilerManager {

        public void addBoilerFuel(EngineFuel fuel) {
            try {
                FuelManager.addBoilerFuel(fuel.getLiquid(), (int) (fuel.getBurnTime() / RailcraftTuning.Tuning.Boiler_PfFConversionRate));
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class CokeOvenManager {

        public void addRecipe(ItemStack input, ItemStack output, LiquidStack liquidOutput, int cookTime) {
            try {
                RailcraftCraftingManager.cokeOven.addRecipe(input, output, liquidOutput, cookTime);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }
}
