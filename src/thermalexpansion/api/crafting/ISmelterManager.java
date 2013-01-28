/**
 * Team CoFH
 * 
 * Thermal Expansion
 */

package thermalexpansion.api.crafting;

import net.minecraft.item.ItemStack;


/**
 * Provides an interface to the recipe manager of the Induction Smelter. Accessible via {@link CraftingManagers.smelterManager}
 */
public interface ISmelterManager {

    /**
     * Add a recipe to the Induction Smelter
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param inputPrimary
     *            ItemStack representing the primary input item.
     * @param inputSecondary
     *            ItemStack representing the secondary input item.
     * @param outputPrimary
     *            ItemStack representing the primary (only) output product.
     */
    boolean addRecipe(int energy, ItemStack inputPrimary, ItemStack inputSecondary, ItemStack outputPrimary);

    /**
     * Add a recipe to the Induction Smelter
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param inputPrimary
     *            ItemStack representing the primary input item.
     * @param inputSecondary
     *            ItemStack representing the secondary input item.
     * @param outputPrimary
     *            ItemStack representing the primary output product.
     * @param outputSecondary
     *            ItemStack representing the secondary output product. Product % is taken to be 100.
     */
    boolean addRecipe(int energy, ItemStack inputPrimary, ItemStack inputSecondary, ItemStack outputPrimary, ItemStack outputSecondary);

    /**
     * Add a recipe to the Induction Smelter
     * 
     * @param energy
     *            Energy needed to process the item.
     * @param inputPrimary
     *            ItemStack representing the primary input item.
     * @param inputSecondary
     *            ItemStack representing the secondary input item.
     * @param outputPrimary
     *            ItemStack representing the primary output product.
     * @param outputSecondary
     *            ItemStack representing the secondary output product.
     * @param secondaryChance
     *            Integer representing % chance (out of 100) of the secondary product being created.
     */
    boolean addRecipe(int energy, ItemStack inputPrimary, ItemStack inputSecondary, ItemStack outputPrimary, ItemStack outputSecondary, int secondaryChance);

    /**
     * Access to the list of recipes.
     */
    ISmelterRecipe[] getRecipeList();
}