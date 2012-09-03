package net.minecraft.src.denoflionsx.OldUnusedCode;

import java.util.ArrayList;
import net.minecraft.src.*;

public class waxSlab extends multiBlock {

    public static int id;
    public static boolean b;

    public waxSlab(int par1, Material par2Material, String name) {
        super(par1, par2Material, name);
        id = par1;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        ModLoader.registerBlock(this, waxSlabItem.class);
        b = false;
    }

    @Override
    public boolean isOpaqueCube() {
        return b;
    }

    @Override
    public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5) {
        super.onBlockPlaced(par1World, par2, par3, par4, par5);
        if (par1World.getBlockId(par2, par3 - 1, par4) == this.blockID) {
            par1World.setBlock(par2, par3, par4, 0);
            par1World.setBlock(par2, par3 - 1, par4, 0);
            par1World.setBlock(par2, par3 - 1, par4, waxBlock.id);
        } else if (par1World.getBlockId(par2, par3 + 1, par4) == this.blockID) {
            par1World.setBlock(par2, par3, par4, 0);
            par1World.setBlock(par2, par3 + 1, par4, 0);
            par1World.setBlock(par2, par3 + 1, par4, waxBlock.id);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        if (b) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            boolean var5 = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0;

            if (var5) {
                this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return b;
    }

    @Override
    public void getCollidingBoundingBoxes(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, ArrayList par6ArrayList) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
    }
}
