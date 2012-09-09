package denoflionsx.core;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class FMLWrapper {
    
    public static enum MODE{
        MODLOADER(),
        FML();
        
        private String mode;

        private MODE() {
            this.mode = this.toString();
        }
        
        public void addRecipe(ItemStack output, Object[] grid){
            if (mode.equals("MODLOADER")){
                ModLoader.addRecipe(output, grid);
            }else if (mode.equals("FML")){
                GameRegistry.addRecipe(output, grid);
            }
        }
        
        public void addShapelessRecipe(ItemStack output, Object[] grid){
            if (mode.equals("MODLOADER")){
                ModLoader.addShapelessRecipe(output, grid);
            }else if(mode.equals("FML")){
                GameRegistry.addShapelessRecipe(output, grid);
            }
        }
        
        public void registerBlock(Block b) {
            if (this.mode.equals("MODLOADER")) {
                ModLoader.registerBlock(b);
            } else if (this.mode.equals("FML")) {
                GameRegistry.registerBlock(b);
            }
        }
        
        public void registerTileEntity(Class t, String name){
            if (this.mode.equals("MODLOADER")){
                ModLoader.registerTileEntity(t, name);
            }else if (this.mode.equals("FML")){
                GameRegistry.registerTileEntity(t, name);
            }
        }
        
        public void registerFuelHandler(IFuelHandler f){
            if (this.mode.equals("MODLOADER")){
                core.print("Function registerFuelHandler is not implemented for MODE MODLOADER");
            }else if (this.mode.equals("FML")){
                GameRegistry.registerFuelHandler(f);
            }
        }
        
        public void addSmelt(ItemStack input, ItemStack output){
            if (this.mode.equals("MODLOADER")){
                ModLoader.addSmelting(input.itemID, output.copy());
            }else if (this.mode.equals("FML")){
                GameRegistry.addSmelting(input.itemID, output.copy(), 1);
            }
        }
    }
    
}
