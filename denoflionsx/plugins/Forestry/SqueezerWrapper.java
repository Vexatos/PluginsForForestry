package denoflionsx.plugins.Forestry;

import buildcraft.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public class SqueezerWrapper {
    
    // This class is to ease the use of the Squeezer API and to protect against...
    /// API changes. 

    private static int bucket = 1000;
    private static int hundred = 100;

    public static void add(ItemStack input, ItemStack output, int chance, ItemStack liquid, int amount) {
        if (liquid == null && output != null) {
            RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[]{input}, new LiquidStack(Block.waterStill, 0), output, chance);
        } else if (liquid != null && output != null) {
            RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[]{input}, new LiquidStack(liquid.getItem(), amount), output, chance);
        } else if (liquid != null && output == null){
            RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[]{input}, new LiquidStack(liquid.getItem(),amount));
        }
    }

    public static void add(ItemStack input, ItemStack output, int chance, ItemStack liquid) {
        add(input, output, chance, liquid, bucket);
    }

    public static void add(ItemStack input, ItemStack output, int chance) {
        add(input, output, chance, null, bucket);
    }

    public static void add(ItemStack input, ItemStack output) {
        add(input, output, hundred, null, bucket);
    }
    public static void add(ItemStack input, LiquidStack liquid){
        add(input, null, hundred, liquid.asItemStack(),liquid.amount);
    }
}
