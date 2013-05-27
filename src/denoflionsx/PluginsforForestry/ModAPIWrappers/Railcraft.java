package denoflionsx.PluginsforForestry.ModAPIWrappers;

import denoflionsx.PluginsforForestry.Core.PfF;
import mods.railcraft.api.crafting.ICokeOvenRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class Railcraft {

    public static void registerCokeOvenRecipe(ItemStack input, ItemStack output, LiquidStack l, int time) {
        try {
            if (RailcraftCraftingManager.cokeOven.getRecipe(input) != null) {
                PfF.Proxy.print("Coke Oven Recipe input already registered! " + input.getItem().getItemDisplayName(input) + "(" + input.itemID + ", " + input.getItemDamage() + ")" + ".");
            } else {
                PfF.Proxy.print("Coke Oven Recipe registered. Input: " + input.getItem().getItemDisplayName(input) + "(" + input.itemID + ", " + input.getItemDamage() + ")" + ". Output: " + output.getItem().getItemDisplayName(output) + "(" + output.itemID + ", " + output.getItemDamage() + ")" + ".");
            }
            RailcraftCraftingManager.cokeOven.addRecipe(input, true, false, output, l, time);
            ICokeOvenRecipe r = RailcraftCraftingManager.cokeOven.getRecipe(input);
            if (!r.getOutput().isItemEqual(output)) {
                PfF.Proxy.print("Coke Oven Recipe Malfunction!");
                PfF.Proxy.print("Original: " + output.getItem().getItemDisplayName(output) + "(" + output.itemID + ", " + output.getItemDamage() + ")" + ". Returned: " + r.getOutput().getItem().getItemDisplayName(r.getOutput()) + "(" + r.getOutput().itemID + ", " + r.getOutput().getItemDamage() + ")" + ".");
            }
        } catch (NoClassDefFoundError ex) {
        }
    }
}
