package denoflionsx.plugins.Forestry;

import denoflionsx.core.core;
import forestry.api.core.BlockInterface;
import net.minecraft.src.Block;

public class ForestryBlock {

    // Bridges the new Forestry API block stuff for old code.
    static {
        core.print("Wrapping Forestry 1.5 API to old 1.4 calls.");
    }
    public static final Block soil = Block.blocksList[BlockInterface.getBlock("soil").itemID];
    public static final Block engine = Block.blocksList[BlockInterface.getBlock("engine").itemID];
    public static final Block planter = Block.blocksList[BlockInterface.getBlock("planter").itemID];
    public static final Block harvester = Block.blocksList[BlockInterface.getBlock("harvester").itemID];
    public static final Block machine = Block.blocksList[BlockInterface.getBlock("machine").itemID];
    public static final Block mill = Block.blocksList[BlockInterface.getBlock("mill").itemID];
}
