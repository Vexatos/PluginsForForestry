package denoflionsx.plugins.Forestry.Helpers;

import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class CarpenterHelper {
    
    public static void addRecipeNoLiquid(ItemStack output, Object[] input){
        RecipeManagers.carpenterManager.addRecipe(10, null, output, input);
    }
    
    public static void addRecipeWithLiquid(ItemStack output, LiquidStack liquid, Object[] input){
        RecipeManagers.carpenterManager.addRecipe(10, liquid, null, output, input);
    }
    
}
