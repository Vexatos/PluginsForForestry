package net.minecraft.server.denoflionsx.plugins.IC2;

import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;

public class TileEntityApiary extends TileEntityMachine
{
    private ItemStack[] Bees = new ItemStack[2];
    private ItemStack[] Products = new ItemStack[7];
    private ItemStack[] Cards = new ItemStack[4];

    public TileEntityApiary(int var1)
    {
        super(var1);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void q_() {}

    /**
     * Writes a tile entity to NBT.
     */
    public void b(NBTTagCompound var1)
    {
        super.b(var1);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void a(NBTTagCompound var1)
    {
        super.a(var1);
    }

    public void applyCardEffects()
    {
        ItemStack[] var1 = this.Cards;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            ItemStack var10000 = var1[var3];
        }
    }
}
