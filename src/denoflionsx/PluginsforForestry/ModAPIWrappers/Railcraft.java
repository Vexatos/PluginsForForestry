package denoflionsx.PluginsforForestry.ModAPIWrappers;

import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class Railcraft {

    public static void registerCokeOvenRecipe(ItemStack input, ItemStack output, LiquidStack l, int time) {
        try {
            RailcraftCraftingManager.cokeOven.addRecipe(input, true, false, output, l, time);
        } catch (NoClassDefFoundError ex) {
        }
    }
}
