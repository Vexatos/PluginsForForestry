package net.minecraft.server.denoflionsx.items;

import net.minecraft.server.denoflionsx.denLib.block_templates.multiBlock;
import java.util.Random;
import net.minecraft.server.Material;
import net.minecraft.server.ModLoader;

public class waxBlock extends multiBlock
{
    public static int id;

    public waxBlock(int var1, Material var2, String var3)
    {
        super(var1, var2, var3);
        id = var1;
        ModLoader.registerBlock(this, waxBlockItem.class);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int getDropType(int var1, Random var2, int var3)
    {
        return waxSlab.id;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int a(Random var1)
    {
        return 2;
    }
}
