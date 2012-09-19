package denoflionsx.Enums;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EnumDyeColors {
    
    // This is to help when using dyes to craft.
    
    public static enum DYES{
        
        INK(0),
        RED(1),
        GREEN(2),
        COCOABEANS(3),
        BLUE(4),
        PURPLE(5),
        CYAN(6),
        LIGHTGREY(7),
        GREY(8),
        PINK(9),
        LIME(10),
        YELLOW(11),
        LIGHTBLUE(12),
        MAGENTA(13),
        ORANGE(14),
        BONEMEAL(15);
        
        private int meta;
        
        private DYES(int meta){
            this.meta = meta;
        }
        
        public ItemStack getDye(){
            return new ItemStack(Item.dyePowder,1,this.meta);
        }
    }
    
}
