
package net.minecraft.src.denoflionsx.items;

import net.minecraft.src.denoflionsx.denLib.Colors;

public class Placeholder {
    
    public static enum Sprite{
        RADICAL_EDWARD;
        
        int index;
        private Sprite(){
            this.index = Colors.shiftRow(0, 0);
        }

        public int getIndex() {
            return this.index;
        }
    }
    
}
