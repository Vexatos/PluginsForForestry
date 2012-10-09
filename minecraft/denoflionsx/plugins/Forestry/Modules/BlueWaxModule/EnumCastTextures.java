package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.Enums.Colors;

public enum EnumCastTextures {
    
    REFRACTORY(Colors.shiftRow(14, 3)),
    WATER(Colors.shiftRow(14, 4)),
    LAVA(Colors.shiftRow(15, 4));
    private int index;
    private EnumCastTextures(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
