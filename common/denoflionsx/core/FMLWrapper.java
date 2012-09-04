package denoflionsx.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;

public class FMLWrapper {
    
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
                GameRegistry.addRecipe(output, grid);
            }
        }
        
        public void addShapelessRecipe(ItemStack output, Object[] grid){
            if (mode.equals("ModLoader")){
                ModLoader.addShapelessRecipe(output, grid);
            }else if(mode.equals("FML")){
                GameRegistry.addShapelessRecipe(output, grid);
            }
        }
        
        public void registerBlock(Block b) {
            if (this.mode.equals("ModLoader")) {
                ModLoader.registerBlock(b);
            } else if (this.mode.equals("FML")) {
                GameRegistry.registerBlock(b);
            }
        }
        
        public void registerTileEntity(Class t, String name){
            if (this.mode.equals("ModLoader")){
                ModLoader.registerTileEntity(t, name);
            }else if (this.mode.equals("FML")){
                GameRegistry.registerTileEntity(t, name);
            }
        }
    }
    
}
