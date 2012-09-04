package denoflionsx.API;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.src.ItemStack;

public class OvenRecipeManager {
    
    private static ArrayList<OvenRecipe> recipes = new ArrayList();
    
    public static void addRecipe(ItemStack output, ItemStack[] recipe, int burnTime){
        recipes.add(new OvenRecipe(output,recipe,burnTime));
    }
    
    public static void addRecipe(ItemStack output, ItemStack[] recipe){
        recipes.add(new OvenRecipe(output,recipe,OvenRecipe.defaultTime));
    }
    
    public static void addRecipe(ItemStack output, ArrayList<ItemStack> recipe, int burnTime){
        recipes.add(new OvenRecipe(output,recipe,burnTime));
    }
    
    public static void addRecipe(ItemStack output, ArrayList<ItemStack> recipe){
        recipes.add(new OvenRecipe(output,recipe,OvenRecipe.defaultTime));
    }
    
    public static boolean isRecipe(ItemStack[] grid){
        return checkRecipe(grid);
    }
    
    public static boolean isRecipe(ArrayList<ItemStack> grid){
        return checkRecipe(toArray(grid));
    }
    
    public static OvenRecipe getRecipeResult(ItemStack[] grid){
        return getOutput(grid);
    }
    
    public static OvenRecipe getRecipeResult(ArrayList<ItemStack> grid){
        return getOutput(toArray(grid));
    }
    
    private static OvenRecipe getOutput(ItemStack[] grid){
        for (OvenRecipe r : recipes){
            if (r.isRecipeEqual(grid)){
                return r;
            }
        }
        return null;
    }
    
    private static boolean checkRecipe(ItemStack[] grid){
        for (OvenRecipe r : recipes){
            if (r.isRecipeEqual(grid)){
                return true;
            }
        }
        return false;
    }
    
    public static ItemStack[] toArray(ArrayList<ItemStack> list){
        return list.toArray(new ItemStack[]{});
    }

    public static class OvenRecipe{
        
        private ItemStack output;
        private ItemStack[] recipe = new ItemStack[9];
        private int cookTime;
        private static final int defaultTime = (2 * 20);

        public OvenRecipe(ItemStack output, ItemStack[] recipe, int cookTime) {
            this.output = output;
            this.recipe = recipe;
            this.cookTime = cookTime;
        }
        
        public OvenRecipe(ItemStack output, ItemStack[] recipe){
            this.output = output;
            this.recipe = recipe;
            this.cookTime = defaultTime;
        }
        
        public OvenRecipe(ItemStack output, ArrayList<ItemStack> recipe, int cookTime){
            this.output = output;
            this.recipe = toArray(recipe);
            this.cookTime = cookTime;
        }
        
        public OvenRecipe(ItemStack output, ArrayList<ItemStack> recipe){
            this.output = output;
            this.recipe = toArray(recipe);
            this.cookTime = defaultTime;
        }
        
        public boolean isRecipeEqual(ItemStack[] recipe){
            return Arrays.equals(this.recipe,recipe);
        }
        
        public boolean isRecipeEqual(ArrayList<ItemStack> recipe){
            return Arrays.equals(this.recipe,toArray(recipe));
        }

        public int getCookTime() {
            return cookTime;
        }

        public ItemStack getOutput() {
            return output;
        }

        public ItemStack[] getRecipe() {
            return recipe;
        }
        
    }
    
}
