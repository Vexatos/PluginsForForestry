package denoflionsx.plugins.Railcraft.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.src.ItemStack;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class RockCrusherRecipeHelper {
    
    public static void add(RockCrusherRecipeHelper.Recipe r){
        RailcraftCraftingManager.rockCrusher.addRecipe(r.getInput(), r.GetOutputsAsHashMap());
    }
    
    public static class Recipe{
        
        private ItemStack input;
        private ArrayList<PotentialOutput> outputs = new ArrayList();

        public Recipe(ItemStack input) {
            this.input = input;
        }
        
        public void addOutput(ItemStack item, float chance){
            outputs.add(new PotentialOutput(item,chance));
        }

        public HashMap<ItemStack,Float> GetOutputsAsHashMap(){
            HashMap<ItemStack,Float> o = new HashMap();
            for (PotentialOutput p : outputs){
                o.put(p.getItem(),p.getChance());
            }
            return o;
        }

        public ItemStack getInput() {
            return input;
        }
    }
    
    public static class PotentialOutput{
        private ItemStack item;
        private float chance;

        public PotentialOutput(ItemStack item, float chance) {
            this.item = item;
            this.chance = chance;
        }

        public PotentialOutput(ItemStack item) {
            this.item = item;
            this.chance = 1.0f;
        }

        public float getChance() {
            return chance;
        }

        public ItemStack getItem() {
            return item;
        }
    }
    
}
