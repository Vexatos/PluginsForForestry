package net.minecraft.server.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.server.BlockContainer;
import net.minecraft.server.Material;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;

public class BlockUniversalSapling extends BlockContainer
{
    private int meta = 0;

    public BlockUniversalSapling(int var1)
    {
        super(var1, Material.CLAY);
        this.c(1.5F);
        this.a("usapling");
        this.textureId = 1;
    }

    public boolean o()
    {
        return true;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int a(int var1, int var2)
    {
        this.meta = var2;
        return super.a(var1, var2);
    }

    public boolean hasTileEntity(int var1)
    {
        return true;
    }

    /**
     * Returns the TileEntity used by this block.
     */
    public TileEntity a_()
    {
        return new TileUniversalSapling();
    }

    public TileEntity getBlockEntity(int var1)
    {
        return new TileUniversalSapling();
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onPlace(World var1, int var2, int var3, int var4)
    {
        var1.setRawData(var2, var3, var4, this.meta);
    }
}
