package net.minecraft.src.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.src.Block;

public class universalTreeData {
    
    protected Block log;
    protected Block leaf;
    protected Block sapling;
    protected int saplingmeta;
    protected int logmeta;
    protected int leafmeta;

    public universalTreeData(Block log, Block leaf, Block sapling, int logmeta, int leafmeta, int saplingmeta) {
        this.log = log;
        this.leaf = leaf;
        this.logmeta = logmeta;
        this.leafmeta = leafmeta;
        this.sapling = sapling;
        this.saplingmeta = saplingmeta;
    }

    public universalTreeData(Block log, Block leaf, Block sapling) {
        this.log = log;
        this.leaf = leaf;
        this.sapling = sapling;
        this.logmeta = 0;
        this.leafmeta = 0;
        this.saplingmeta = 0;
    }
    
    public Block getLog(){
        return log;
    }
    
    public Block getLeaf(){
        return leaf;
    }
    
    public int getLogMeta(){
        return logmeta;
    }
    
    public int getLeafMeta(){
        return leafmeta;
    }
}
