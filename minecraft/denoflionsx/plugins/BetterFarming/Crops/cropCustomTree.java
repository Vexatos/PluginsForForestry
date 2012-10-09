package denoflionsx.plugins.BetterFarming.Crops;

import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.cultivation.ICropEntity;

public class cropCustomTree implements ICropEntity {

    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private int blockid;
    private int meta;
    private int woodid;
    private int woodmeta;

    public cropCustomTree(World var1, int var2, int var3, int var4) {

        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.blockid = var1.getBlockId(var2, var3, var4);
        this.meta = var1.getBlockMetadata(var2, var3, var4);
        this.woodid = Block.wood.blockID;
        this.woodmeta = 0;
    }

    public cropCustomTree(World var1, int var2, int var3, int var4, int var5, int var6) {

        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.blockid = var1.getBlockId(var2, var3, var4);
        this.meta = var1.getBlockMetadata(var2, var3, var4);
        this.woodid = var5;
        this.woodmeta = var6;
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList var1 = Block.blocksList[this.woodid].getBlockDropped(this.world, this.xCoord, this.yCoord, this.zCoord, this.woodmeta, 0);
        this.world.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }

    @Override
    public int[] getNextPosition() {
        int[] var1 = null;
        int var2 = 1;
        int var3;

        for (var3 = this.world.getBlockId(this.xCoord, this.yCoord + var2, this.zCoord); var3 == Block.wood.blockID; var3 = this.world.getBlockId(this.xCoord, this.yCoord + var2, this.zCoord)) {
            var1 = new int[]{this.xCoord, this.yCoord + var2, this.zCoord};
            ++var2;
        }

        if (var1 != null) {
            return var1;
        } else {
            var2 = -1;

            for (var3 = this.world.getBlockId(this.xCoord, this.yCoord + var2, this.zCoord); var3 == Block.wood.blockID; var3 = this.world.getBlockId(this.xCoord, this.yCoord + var2, this.zCoord)) {
                var1 = new int[]{this.xCoord, this.yCoord + var2, this.zCoord};
                --var2;
            }

            return var1;
        }
    }

    @Override
    public boolean isHarvestable() {
        if (this.blockid == this.woodid && this.meta == this.woodmeta) {    
            return true;
        }
        return false;
    }
}
