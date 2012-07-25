package net.minecraft.src.denoflionsx.plugins.IC2;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.GUIs.baseGUI;

public class BlockMachine extends BlockContainer{
    
    protected TileEntityMachine entity;

    public BlockMachine(int par1, Material par2Material) {
        super(par1, par2Material);
        this.blockIndexInTexture = 1;
    }

    @Override
    public TileEntity getBlockEntity() {
        return new TileEntityMachine(10000);
    }

    @Override
    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        par5EntityPlayer.openGui(mod_PluginsforForestry.instance, 1, par1World, par2, par3, par4);
        return true;
    }     
}
