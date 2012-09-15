package denoflionsx.Enums;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EnumDyeColors {
    
    // This is to help when using dyes to craft.
    
    public static int ColorMeta = 0;
    
    public static enum DYES{
        
        INK(),
        RED(),
        GREEN(),
        COCOABEANS(),
        BLUE(),
        PURPLE(),
        CYAN(),
        LIGHTGREY(),
        GREY(),
        PINK(),
        LIME(),
        YELLOW(),
        LIGHTBLUE(),
        MAGENTA(),
        ORANGE(),
        BONEMEAL();
        
        private int meta;
        
        private DYES(){
            this.meta = ColorMeta++;
        }
        
        public ItemStack getDye(){
            return new ItemStack(Item.dyePowder,1,this.meta);
        }
    }
    
}
