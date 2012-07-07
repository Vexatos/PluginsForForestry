package net.minecraft.server.denoflionsx.plugins.BetterFarming;

import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class cropCustomSeed implements ICropEntity
{
    protected World world;
    protected int xCoord;
    protected int yCoord;
    protected int zCoord;
    protected int meta;
    protected int plantid;
    protected int seedid;
    protected int grownmeta;
    protected int plantamount;
    protected int seedamount;

    public cropCustomSeed(World var1, int var2, int var3, int var4, Item var5, Item var6, int var7)
    {
        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.meta = var1.getData(var2, var3, var4);
        this.plantid = var5.id;
        this.seedid = var6.id;
        this.grownmeta = var7;
        this.plantamount = 1;
        this.seedamount = 2;
    }

    public cropCustomSeed(World var1, int var2, int var3, int var4, Item var5, Item var6, int var7, int[] var8)
    {
        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.meta = var1.getData(var2, var3, var4);
        this.plantid = var5.id;
        this.seedid = var6.id;
        this.grownmeta = var7;
        this.plantamount = var8[0];
        this.seedamount = var8[1];
    }

    public ArrayList doHarvest()
    {
        ArrayList var1 = new ArrayList();
        var1.add(new ItemStack(this.plantid, this.plantamount, 0));
        var1.add(new ItemStack(this.seedid, this.seedamount, 0));
        this.world.setTypeIdAndData(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }

    public int[] getNextPosition()
    {
        return null;
    }

    public boolean isHarvestable()
    {
        return this.meta >= this.grownmeta;
    }
}
