package net.minecraft.src.denoflionsx.plugins.Core;

import net.minecraft.src.denoflionsx.denLib.Colors;

public class EnumToolTextures {
    
    public static enum ToolTextures{
        
        BLACKSMITHHAMMER(Colors.shiftRow(2, 6)),
        IRONRING(Colors.shiftRow(3,6));
        
        private int index;
        
        private ToolTextures(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
    
}
