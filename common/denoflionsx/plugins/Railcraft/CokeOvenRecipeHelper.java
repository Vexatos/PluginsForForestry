
package denoflionsx.plugins.Railcraft;

import buildcraft.api.liquids.LiquidStack;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class CokeOvenRecipeHelper {
    
    public static ArrayList<CokeOvenRecipeHelper.Recipe> recipes = new ArrayList();
    
    public static void add(CokeOvenRecipeHelper.Recipe r){
        recipes.add(r);
        RailcraftCraftingManager.cokeOven.addRecipe(r.getInput(), r.getOutput(), r.getLiquidStack(), r.getCooktime());
    }
    
    public static class Recipe{
        private ItemStack input;
        private ItemStack output;
        private ItemStack liquid;
        private int amount;
        private int cooktime;

        public Recipe(ItemStack input, ItemStack output, ItemStack liquid, int amount, int cooktime) {
            this.input = input;
            this.output = output;
            this.liquid = liquid;
            this.amount = amount;
            this.cooktime = cooktime;
        }

        public int getCooktime() {
            return cooktime;
        }

        public ItemStack getInput() {
            return input;
        }

        public ItemStack getOutput() {
            return output;
        }
        
        public LiquidStack getLiquidStack(){
            return new LiquidStack(this.liquid.getItem().shiftedIndex,this.amount);
        }  
    }
    
}
