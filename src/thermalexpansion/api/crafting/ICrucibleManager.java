/**
 * Team CoFH
 * 
 * Thermal Expansion
 */

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;



/**
 * Provides an interface to the recipe manager of the Crucible. Accessible via {@link CraftingManagers.crucibleManager}
 */
public interface ICrucibleManager {

    /**
     * Add a recipe to the Crucible
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param input
     *            ItemStack representing the input item.
     * @param output
     *            LiquidStack representing the output liquid.
     */
    boolean addRecipe(int energy, ItemStack input, LiquidStack output);

    /**
     * Access to the full list of recipes.
     */
    ICrucibleRecipe[] getRecipeList();
}