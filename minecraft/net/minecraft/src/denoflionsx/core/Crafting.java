package net.minecraft.src.denoflionsx.core;

import cpw.mods.fml.common.registry.FMLRegistry;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class Crafting {
    
    public static enum MODE{
        MODLOADER("ModLoader"),
        FML("FML");
        
        private String mode;

        private MODE(String mode) {
            this.mode = mode;
        }
        
        public void addRecipe(ItemStack output, Object[] grid){
            if (mode.equals("ModLoader")){
                ModLoader.addRecipe(output, grid);
            }else if (mode.equals("FML")){
                FMLRegistry.addRecipe(output, grid);
            }
        }
        
        public void addShapelessRecipe(ItemStack output, Object[] grid){
            if (mode.equals("ModLoader")){
                ModLoader.addShapelessRecipe(output, grid);
            }else if(mode.equals("FML")){
                FMLRegistry.addShapelessRecipe(output, grid);
            }
        }
    }
    
}
