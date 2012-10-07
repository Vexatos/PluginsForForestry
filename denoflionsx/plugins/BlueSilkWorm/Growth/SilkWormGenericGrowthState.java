package denoflionsx.plugins.BlueSilkWorm.Growth;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGrowthState;

public class SilkWormGenericGrowthState implements ISilkWormGrowthState{

    private int meta;
    private int texture;

    public SilkWormGenericGrowthState(int meta, int texture) {
        this.meta = meta;
        this.texture = texture;
    }
    
    @Override
    public int getMeta() {
        return meta;
    }

    @Override
    public int getTexture() {
        return texture;
    }
    
}
