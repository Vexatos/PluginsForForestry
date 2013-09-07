package denoflionsx.PluginsforForestry.ModAPIWrappers;

import denoflionsx.PluginsforForestry.Core.PfF;
import mods.railcraft.api.crafting.ICokeOvenRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Railcraft {

    public static void registerCokeOvenRecipe(ItemStack input, ItemStack output, FluidStack l, int time) {
        try {
            if (RailcraftCraftingManager.cokeOven.getRecipe(input) != null) {
                PfF.Proxy.print("Coke Oven Recipe input already registered! Expected Input: " + getItemInfo(input) + "Returned: " + getItemInfo(RailcraftCraftingManager.cokeOven.getRecipe(input).getInput()));
            } else {
                PfF.Proxy.print("Coke Oven Recipe registered. Input: " + getItemInfo(input) + "Output: " + getItemInfo(output));
            }
            RailcraftCraftingManager.cokeOven.addRecipe(input, true, false, output, l, time);
            ICokeOvenRecipe r = RailcraftCraftingManager.cokeOven.getRecipe(input);
            if (!r.getOutput().isItemEqual(output)) {
                PfF.Proxy.print("Expected Output: " + getItemInfo(output) + "Returned: " + getItemInfo(r.getOutput()));
            }
        } catch (NoClassDefFoundError ex) {
        }
    }

    private static String getItemInfo(ItemStack stack) {
        String itemName = stack.getItem().getItemDisplayName(stack);
        if (itemName.equals("")) {
            itemName = stack.getItemName();
        }
        return itemName + "(" + stack.itemID + ", " + stack.getItemDamage() + "). ";
    }
}
