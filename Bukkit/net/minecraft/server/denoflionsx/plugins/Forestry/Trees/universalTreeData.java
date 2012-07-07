package net.minecraft.server.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.server.Block;

public class universalTreeData
{
    protected Block log;
    protected Block leaf;
    protected Block sapling;
    protected int saplingmeta;
    protected int logmeta;
    protected int leafmeta;

    public universalTreeData(Block var1, Block var2, Block var3, int var4, int var5, int var6)
    {
        this.log = var1;
        this.leaf = var2;
        this.logmeta = var4;
        this.leafmeta = var5;
        this.sapling = var3;
        this.saplingmeta = var6;
    }

    public universalTreeData(Block var1, Block var2, Block var3)
    {
        this.log = var1;
        this.leaf = var2;
        this.sapling = var3;
        this.logmeta = 0;
        this.leafmeta = 0;
        this.saplingmeta = 0;
    }

    public Block getLog()
    {
        return this.log;
    }

    public Block getLeaf()
    {
        return this.leaf;
    }

    public int getLogMeta()
    {
        return this.logmeta;
    }

    public int getLeafMeta()
    {
        return this.leafmeta;
    }
}
