package denoflionsx.plugins.Forestry.Modules.bluesWax;

import denoflionsx.Enums.Colors;

public enum EnumCastTextures {
    
    REFRACTORY(Colors.shiftRow(15, 3)),
    WATER(Colors.shiftRow(15, 4)),
    LAVA(Colors.shiftRow(15, 5));
    private int index;
    private EnumCastTextures(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
