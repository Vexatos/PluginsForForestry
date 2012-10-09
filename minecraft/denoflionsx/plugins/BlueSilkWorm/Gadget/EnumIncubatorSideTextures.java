package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Enums.Colors;

public enum EnumIncubatorSideTextures {
    
    TOP(Colors.shiftRow(9, 8)),
    SIDE(Colors.shiftRow(7, 8)),
    BOTTOM(Colors.shiftRow(8, 8));
    
    private int index;
    private EnumIncubatorSideTextures(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
