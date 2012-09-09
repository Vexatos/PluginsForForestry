package denoflionsx.plugins.Forestry;

import buildcraft.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.ItemStack;

public class FermenterHelper {
    
    public static void add(ItemStack fermentedItem, ItemStack inputLiquid, int amountUsed, ItemStack outputLiquid, int amountProduced, float bonus){
        FermenterHelper.Recipe recipe = new FermenterHelper.Recipe(fermentedItem, inputLiquid, amountUsed, outputLiquid, amountProduced, bonus);
        RecipeManagers.fermenterManager.addRecipe(recipe.getFermentedItem(),recipe.getAmountProduced(),recipe.getBonus(),recipe.getOutputLiquidStack(),recipe.getInputLiquidStack());
    }

    public static class Recipe {

        private ItemStack fermentedItem;
        private ItemStack inputLiquid;
        private int amountUsed;
        private ItemStack outputLiquid;
        private int amountProduced;
        private float bonus;

        public Recipe(ItemStack fermentedItem, ItemStack inputLiquid, int amountUsed, ItemStack outputLiquid, int amountProduced, float bonus) {
            this.fermentedItem = fermentedItem;
            this.inputLiquid = inputLiquid;
            this.amountUsed = amountUsed;
            this.outputLiquid = outputLiquid;
            this.amountProduced = amountProduced;
            this.bonus = bonus;
        }

        public int getAmountProduced() {
            return amountProduced;
        }

        public int getAmountUsed() {
            return amountUsed;
        }

        public float getBonus() {
            return bonus;
        }

        public ItemStack getFermentedItem() {
            return fermentedItem;
        }

        public ItemStack getInputLiquid() {
            return inputLiquid;
        }

        public ItemStack getOutputLiquid() {
            return outputLiquid;
        }
        
        public LiquidStack getOutputLiquidStack(){
            return new LiquidStack(this.outputLiquid.getItem().shiftedIndex,this.amountProduced);
        }
        
        public LiquidStack getInputLiquidStack(){
            return new LiquidStack(this.inputLiquid.getItem().shiftedIndex,this.amountUsed);
        }
    }
}
