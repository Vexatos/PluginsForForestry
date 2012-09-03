package net.minecraft.src.denoflionsx.API;

import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.core.core;

public class OvenRecipeManager {

    public static ArrayList<OvenRecipe> recipes = new ArrayList();

    public static void addRecipe(ItemStack output, ItemStack tool, ItemStack[] grid) {
        recipes.add(new OvenRecipe(output, tool, grid));
    }

    public static OvenRecipe findMatchingRecipe(ItemStack tool, ItemStack[] grid) {
        for (OvenRecipe r : recipes) {
            if (r.doesRecipeMatch(tool, grid)) {
                return r;
            }
        }
        return null;
    }

    public static OvenRecipe findMatchingRecipe(ItemStack[] grid) {
        return findMatchingRecipe(null, grid);
    }

    public static class OvenRecipe {

        private ItemStack output;
        private ItemStack tool;
        private ItemStack[] grid = new ItemStack[9];

        public OvenRecipe(ItemStack output, ItemStack tool, ItemStack[] grid) {
            this.output = output;
            this.grid = grid;
        }

        public boolean doesRecipeMatch(ItemStack tool, ItemStack[] test) {
            int i = 0;
            int matches = 0;
            for (ItemStack z : this.grid){
                if (z == test[i]){
                    matches++;
                }
                i++;
            }
            core.print("" + matches);
            if (matches == 8){
                return true;
            }else{
                return false;
            }
        }

        public boolean doesRecipeMatch(ItemStack[] test) {
            return doesRecipeMatch(null, grid);
        }

        public ItemStack getOutput() {
            return output;
        }

        public ItemStack getTool() {
            return tool;
        }
    }
}
