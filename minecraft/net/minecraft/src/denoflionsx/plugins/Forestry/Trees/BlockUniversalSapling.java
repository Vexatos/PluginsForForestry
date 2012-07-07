package net.minecraft.src.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.src.*;

public class BlockUniversalSapling extends BlockContainer {
    
    private int meta = 0;

    public BlockUniversalSapling(int par1) {
        super(par1, Material.clay);
        this.setHardness(1.5F);
        this.setBlockName("usapling");
        this.blockIndexInTexture = 1;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        this.meta = par2;
        return super.getBlockTextureFromSideAndMetadata(par1, par2);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity getBlockEntity() {
        return new TileUniversalSapling();
    }

    @Override
    public TileEntity getBlockEntity(int meta) {
         return new TileUniversalSapling();
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        par1World.setBlockMetadata(par2, par3, par4, this.meta);
    }

    
    
    
}
