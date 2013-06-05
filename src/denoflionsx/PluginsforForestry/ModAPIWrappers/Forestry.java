package denoflionsx.PluginsforForestry.ModAPIWrappers;

import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;
import denoflionsx.PluginsforForestry.Utils.FermenterUtils.FermenterRecipe;
import forestry.api.core.BlockInterface;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class Forestry {

    private static final void template() {
        try {
        } catch (NoClassDefFoundError ex) {
        }
    }

    public static ItemStack items(String tag) {
        try {
            return ItemInterface.getItem(tag);
        } catch (NoClassDefFoundError ex) {
        }
        return null;
    }

    public static void squeezer(int time, ItemStack[] grid, LiquidStack l) {
        try {
            if (RecipeManagers.squeezerManager != null) {
                RecipeManagers.squeezerManager.addRecipe(time, grid, l);
            }
        } catch (NoClassDefFoundError ex) {
        }
    }

    public static ItemStack block(String name) {
        try {
            return BlockInterface.getBlock(name);
        } catch (NoClassDefFoundError ex) {
        }
        return null;
    }

    public static void biogas(EngineFuel fuel, int safety) {
        try {
            if (FuelManager.bronzeEngineFuel != null) {
                FuelManager.bronzeEngineFuel.put(fuel.getLiquidStack().asItemStack(), new EngineBronzeFuel(fuel.getLiquidStack().asItemStack(), fuel.getMJt(), fuel.getBurnTime(), safety));
            }
        } catch (NoClassDefFoundError ex) {
        }
    }

    public static ArrayList<EngineFuel> getFuelMap() {
        try {
            ArrayList<EngineFuel> f = new ArrayList();
            if (FuelManager.bronzeEngineFuel != null) {
                for (EngineBronzeFuel value : FuelManager.bronzeEngineFuel.values()) {
                    if (value.dissipationMultiplier == 1) {
                        EngineFuel fuel = new EngineFuel(LiquidDictionary.findLiquidName(new LiquidStack(value.liquid.itemID, 1000, value.liquid.getItemDamage())), value.powerPerCycle, value.burnDuration);
                        f.add(fuel);
                    }
                }
            }
            return f;
        } catch (NoClassDefFoundError ex) {
        }
        return null;
    }

    public static void fermenter(FermenterRecipe z, float bonus, LiquidStack liquid, String target) {
        try {
            if (RecipeManagers.fermenterManager != null) {
                RecipeManagers.fermenterManager.addRecipe(z.getFermentable(), z.getAmount(), 1.5f, LiquidDictionary.getLiquid(target, LiquidContainerRegistry.BUCKET_VOLUME), liquid);
            }
        } catch (NoClassDefFoundError ex) {
        }
    }

    public static void carpenter(ItemStack output, Object[] grid, LiquidStack liquid) {
        try {
            RecipeManagers.carpenterManager.addRecipe(5, liquid, null, output, grid);
        } catch (NoClassDefFoundError ex) {
        }
    }
}
