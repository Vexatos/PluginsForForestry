package net.minecraft.server.denoflionsx.plugins.IC2;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.BlockContainer;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Material;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;

public class BlockMachine extends BlockContainer
{
    protected TileEntityMachine entity;

    public BlockMachine(int var1, Material var2)
    {
        super(var1, var2);
        this.textureId = 1;
    }

    /**
     * Returns the TileEntity used by this block.
     */
    public TileEntity a_()
    {
        return new TileEntityMachine(10000);
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    public boolean interact(World var1, int var2, int var3, int var4, EntityHuman var5)
    {
        TileEntityMachine var6 = (TileEntityMachine)var1.getTileEntity(var2, var3, var4);
        String var7 = String.valueOf(var6.getStoredEU());
        core.print("stored eu: " + var7);
        var7 = String.valueOf(var6.getStoredLiquid());
        core.print("stored liquid: " + var7);
        return super.interact(var1, var2, var3, var4, var5);
    }
}
