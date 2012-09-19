package denoflionsx.plugins.Railcraft.Managers;

import denoflionsx.Interfaces.IOreDictRecipeManager;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class BlastFurnaceManager implements IOreDictRecipeManager {

    @Override
    public void addRecipes(ItemStack output, ArrayList<ItemStack> ores) {
         RailcraftCraftingManager.blastFurnace.addRecipe(output.itemID, output.getItemDamage(), 20, ores.get(0));
    }
}
