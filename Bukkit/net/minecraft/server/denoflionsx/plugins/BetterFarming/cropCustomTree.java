package net.minecraft.server.denoflionsx.plugins.BetterFarming;

import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.server.Block;
import net.minecraft.server.World;

public class cropCustomTree implements ICropEntity
{
    private World world;
    private int xCoord;
    private int yCoord;
    private int zCoord;
    private int blockid;
    private int meta;
    private int woodid;
    private int woodmeta;

    public cropCustomTree(World var1, int var2, int var3, int var4)
    {
        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.blockid = var1.getTypeId(var2, var3, var4);
        this.meta = var1.getData(var2, var3, var4);
        this.woodid = Block.LOG.id;
        this.woodmeta = 0;
    }

    public cropCustomTree(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.blockid = var1.getTypeId(var2, var3, var4);
        this.meta = var1.getData(var2, var3, var4);
        this.woodid = var5;
        this.woodmeta = var6;
    }

    public ArrayList doHarvest()
    {
        ArrayList var1 = Block.byId[this.woodid].getBlockDropped(this.world, this.xCoord, this.yCoord, this.zCoord, this.woodmeta, 0);
        this.world.setTypeIdAndData(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }

    public int[] getNextPosition()
    {
        int[] var1 = null;
        int var2 = 1;
        int var3;

        for (var3 = this.world.getTypeId(this.xCoord, this.yCoord + var2, this.zCoord); var3 == Block.LOG.id; var3 = this.world.getTypeId(this.xCoord, this.yCoord + var2, this.zCoord))
        {
            var1 = new int[] {this.xCoord, this.yCoord + var2, this.zCoord};
            ++var2;
        }

        if (var1 != null)
        {
            return var1;
        }
        else
        {
            var2 = -1;

            for (var3 = this.world.getTypeId(this.xCoord, this.yCoord + var2, this.zCoord); var3 == Block.LOG.id; var3 = this.world.getTypeId(this.xCoord, this.yCoord + var2, this.zCoord))
            {
                var1 = new int[] {this.xCoord, this.yCoord + var2, this.zCoord};
                --var2;
            }

            return var1;
        }
    }

    public boolean isHarvestable()
    {
        return this.blockid == this.woodid && this.meta == this.woodmeta;
    }
}
