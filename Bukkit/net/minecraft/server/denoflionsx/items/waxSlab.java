package net.minecraft.server.denoflionsx.items;

import net.minecraft.server.denoflionsx.denLib.block_templates.multiBlock;
import java.util.ArrayList;
import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.Material;
import net.minecraft.server.ModLoader;
import net.minecraft.server.World;

public class waxSlab extends multiBlock
{
    public static int id;
    public static boolean b;

    public waxSlab(int var1, Material var2, String var3)
    {
        super(var1, var2, var3);
        id = var1;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        ModLoader.registerBlock(this, waxSlabItem.class);
        b = false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean a()
    {
        return b;
    }

    /**
     * Called when a block is placed using an item. Used often for taking the facing and figuring out how to position
     * the item. Args: x, y, z, facing
     */
    public void postPlace(World var1, int var2, int var3, int var4, int var5)
    {
        super.postPlace(var1, var2, var3, var4, var5);

        if (var1.getTypeId(var2, var3 - 1, var4) == this.id)
        {
            var1.setRawTypeId(var2, var3, var4, 0);
            var1.setRawTypeId(var2, var3 - 1, var4, 0);
            var1.setRawTypeId(var2, var3 - 1, var4, waxBlock.id);
        }
        else if (var1.getTypeId(var2, var3 + 1, var4) == this.id)
        {
            var1.setRawTypeId(var2, var3, var4, 0);
            var1.setRawTypeId(var2, var3 + 1, var4, 0);
            var1.setRawTypeId(var2, var3 + 1, var4, waxBlock.id);
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void updateShape(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (b)
        {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else
        {
            boolean var5 = (var1.getData(var2, var3, var4) & 8) != 0;

            if (var5)
            {
                this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            else
            {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean b()
    {
        return b;
    }

    /**
     * Adds to the supplied array any colliding bounding boxes with the passed in bounding box. Args: world, x, y, z,
     * axisAlignedBB, arrayList
     */
    public void a(World var1, int var2, int var3, int var4, AxisAlignedBB var5, ArrayList var6)
    {
        this.updateShape(var1, var2, var3, var4);
        super.a(var1, var2, var3, var4, var5, var6);
    }
}
