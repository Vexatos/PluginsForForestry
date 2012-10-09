package denoflionsx.Enums;

public class EnumToolTextures {
    
    public static enum ToolTextures{
        
        BLACKSMITHHAMMER(Colors.shiftRow(2, 6)),
        IRONRING(Colors.shiftRow(3,6)),
        LIQUIDVACUUM(Colors.shiftRow(3,2)),
        WOODENBUCKET_EMPTY(Colors.shiftRow(3,1)),
        WOODENBUCKET_FULL(Colors.shiftRow(4,1)),
        MILKBAG(Colors.shiftRow(4,2)),
        URANIUMGOO(Colors.shiftRow(3,3)),
        RODOFREEZING(Colors.shiftRow(15,1)),
        DEBUGSTICK(Colors.shiftRow(15,0)),
        MUSHROOMBAG(Colors.shiftRow(4,3)),
        EXTRACTOR(Colors.shiftRow(4,6)),
        WAXTABLET(Colors.shiftRow(15,2)),
        REFRACTORYCAST(Colors.shiftRow(15, 3)),
        REFRACTORYCAST_WATER(Colors.shiftRow(15, 4)),
        REFRACTORYCAST_LAVA(Colors.shiftRow(15, 5));
        
        private int index;
        
        private ToolTextures(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
    
}
