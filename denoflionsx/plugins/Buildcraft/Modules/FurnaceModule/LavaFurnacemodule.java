package denoflionsx.plugins.Buildcraft.Modules.FurnaceModule;

import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import net.minecraft.src.Block;

public class LavaFurnacemodule extends baseModule {
    
    BlockLavaFurnace furnace;

    public LavaFurnacemodule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
    }

    @Override
    protected void init() {
        int id = Block.stoneOvenIdle.blockID;
        Block.blocksList[id] = null;
        furnace = new BlockLavaFurnace(id,false); 
    }

    @Override
    protected void recipes() {
    }

    public static void load(pluginBase parent) {
        baseModule b = new LavaFurnacemodule(parent);
        b.register();
    }
}
