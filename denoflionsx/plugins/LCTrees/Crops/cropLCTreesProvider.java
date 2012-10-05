package denoflionsx.plugins.LCTrees.Crops;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import denoflionsx.core.core;
import denoflionsx.plugins.BetterFarming.Crops.cropCustomTree;
import denoflionsx.plugins.Forestry.Helpers.ForestryBlock;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class cropLCTreesProvider implements ICropProvider {
    
    protected int treeID;
    protected Block tree;
    protected ItemStack windFallData;
    protected int saplingIDs[];
    protected int treeMeta;

    public cropLCTreesProvider(Block param1, ItemStack param2, int param3[]) {
        
        this.tree = param1;
        this.treeID = param1.blockID;
        this.windFallData = param2;
        this.saplingIDs = param3;
        this.treeMeta = 0;
    }
    
    public cropLCTreesProvider(Block param1, ItemStack param2, int param3[], int param4){
        this.tree = param1;
        this.treeID = param1.blockID;
        this.windFallData = param2;
        this.saplingIDs = param3;
        this.treeMeta = param4;
        core.print("tree set to: " + this.tree.getBlockName());
        core.print("tree id set to: " + this.treeID);
        core.print("sapling set to :" + this.saplingIDs[0]);
        core.print("meta set to: " + this.treeMeta);
    }

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int var6 = world.getBlockId(x, y, z);

        if (var6 != 0) {
            int var7 = world.getBlockMetadata(x, y, z);
            if (var7 == 4){
            //growHook.growTree(world,x,y,z,this.tree);
            }
            return false;
        } else {
            int var7 = world.getBlockId(x, y - 1, z);
            int var8 = world.getBlockMetadata(x, y - 1, z);

            if (var7 == ForestryBlock.soil.blockID && (var8 & 3) == 0) {
                world.setBlockAndMetadataWithNotify(x, y, z, this.treeID, this.treeMeta);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropCustomTree(world, x, y, z,this.treeID,this.treeMeta);
    }

    @Override
    public ItemStack[] getWindfall() {
        if (this.windFallData == null){
            return null;
        }
        return new ItemStack[]{this.windFallData};
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        return (id == this.treeID && meta == this.treeMeta);
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        for(int i = 0; i < this.saplingIDs.length; i++){
        if (germling.itemID == this.saplingIDs[i]){
            return true;
        }
    }    
        return false;
    }
}
