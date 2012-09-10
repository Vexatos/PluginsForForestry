package denoflionsx.plugins.Core;

import denoflionsx.denLib.Colors;

public class EnumToolTextures {
    
    public static enum ToolTextures{
        
        BLACKSMITHHAMMER(Colors.shiftRow(2, 6)),
        IRONRING(Colors.shiftRow(3,6)),
        LIQUIDVACUUM(Colors.shiftRow(3,2)),
        WOODENBUCKET_EMPTY(Colors.shiftRow(3,1)),
        WOODENBUCKET_FULL(Colors.shiftRow(4,1)),
        MILKBAG(Colors.shiftRow(4,2)),
        URANIUMGOO(Colors.shiftRow(3,3)),
        RODOFREEZING(Colors.shiftRow(15,5)),
        DEBUGSTICK(Colors.shiftRow(15,6)),
        MUSHROOMBAG(Colors.shiftRow(4,3)),
        EXTRACTOR(Colors.shiftRow(4,6));
        
        private int index;
        
        private ToolTextures(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
    
}
