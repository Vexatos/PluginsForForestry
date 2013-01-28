/**
 * Team CoFH
 * 
 * Thermal Expansion
 */

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;



/**
 * Provides an interface to the recipe manager of the Sawmill. Accessible via {@link CraftingManagers.sawmillManager}
 */
public interface ISawmillManager {

    /**
     * Add a recipe to the Sawmill
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param input
     *            ItemStack representing the input item.
     * @param primary
     *            ItemStack representing the primary (only) output product.
     */
    boolean addRecipe(int energy, ItemStack input, ItemStack primary);

    /**
     * Add a recipe to the Sawmill
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param input
     *            ItemStack representing the input item.
     * @param primary
     *            ItemStack representing the primary output product.
     * @param secondary
     *            ItemStack representing the secondary output product. Product % is taken to be 100.
     */
    boolean addRecipe(int energy, ItemStack input, ItemStack primary, ItemStack secondary);

    /**
     * Add a recipe to the Sawmill
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param input
     *            ItemStack representing the input item.
     * @param outputPrimary
     *            ItemStack representing the primary output product.
     * @param outputSecondary
     *            ItemStack representing the secondary output product.
     * @param secondaryChance
     *            Integer representing % chance (out of 100) of the secondary product being created.
     */
    boolean addRecipe(int energy, ItemStack input, ItemStack primary, ItemStack secondary, int secondaryChance);

    /**
     * Access to the list of recipes.
     */
    ISawmillRecipe[] getRecipeList();
}