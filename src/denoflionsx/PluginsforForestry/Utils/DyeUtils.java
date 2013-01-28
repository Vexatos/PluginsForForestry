package denoflionsx.PluginsforForestry.Utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DyeUtils {
    
    private static int current = -1;
    
    public static enum DYES{
        
        BLACK,
        RED,
        GREEN,
        BROWN,
        BLUE,
        LIGHT_GRAY,
        GRAY,
        PINK,
        LIME,
        YELLOW,
        LIGHT_BLUE,
        MAGENTA,
        ORANGE,
        WHITE;
        
        private ItemStack dye;

        private DYES() {
            current++;
            int meta = current;
            this.dye = new ItemStack(Item.dyePowder,1,meta);
        }

        public ItemStack getDye() {
            return dye;
        }  
    }
    
}
