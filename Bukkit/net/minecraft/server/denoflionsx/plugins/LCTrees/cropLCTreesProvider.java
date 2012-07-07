package net.minecraft.server.denoflionsx.plugins.LCTrees;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomTree;
import forestry.api.core.ForestryBlock;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class cropLCTreesProvider implements ICropProvider
{
    protected int treeID;
    protected Block tree;
    protected ItemStack windFallData;
    protected int[] saplingIDs;
    protected int treeMeta;

    public cropLCTreesProvider(Block var1, ItemStack var2, int[] var3)
    {
        this.tree = var1;
        this.treeID = var1.id;
        this.windFallData = var2;
        this.saplingIDs = var3;
        this.treeMeta = 0;
    }

    public cropLCTreesProvider(Block var1, ItemStack var2, int[] var3, int var4)
    {
        this.tree = var1;
        this.treeID = var1.id;
        this.windFallData = var2;
        this.saplingIDs = var3;
        this.treeMeta = var4;
        core.print("tree set to: " + this.tree.q());
        core.print("tree id set to: " + this.treeID);
        core.print("sapling set to :" + this.saplingIDs[0]);
        core.print("meta set to: " + this.treeMeta);
    }

    public boolean doPlant(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        int var6 = var2.getTypeId(var3, var4, var5);
        int var7;

        if (var6 != 0)
        {
            var7 = var2.getData(var3, var4, var5);

            if (var7 == 4)
            {
                ;
            }

            return false;
        }
        else
        {
            var7 = var2.getTypeId(var3, var4 - 1, var5);
            int var8 = var2.getData(var3, var4 - 1, var5);

            if (var7 == ForestryBlock.soil.id && (var8 & 3) == 0)
            {
                var2.setTypeIdAndData(var3, var4, var5, this.treeID, this.treeMeta);
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public ICropEntity getCrop(World var1, int var2, int var3, int var4)
    {
        return new cropCustomTree(var1, var2, var3, var4, this.treeID, this.treeMeta);
    }

    public ItemStack[] getWindfall()
    {
        return this.windFallData == null ? null : new ItemStack[] {this.windFallData};
    }

    public boolean isCrop(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getTypeId(var2, var3, var4);
        int var6 = var1.getData(var2, var3, var4);
        return var5 == this.treeID && var6 == this.treeMeta;
    }

    public boolean isGermling(ItemStack var1)
    {
        for (int var2 = 0; var2 < this.saplingIDs.length; ++var2)
        {
            if (var1.id == this.saplingIDs[var2])
            {
                return true;
            }
        }

        return false;
    }
}
