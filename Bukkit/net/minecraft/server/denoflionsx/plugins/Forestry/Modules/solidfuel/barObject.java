package net.minecraft.server.denoflionsx.plugins.Forestry.Modules.solidfuel;

import forestry.api.liquids.LiquidStack;
import net.minecraft.server.ItemStack;

public class barObject
{
    private ItemStack bar;
    private LiquidStack liquid;
    private ItemStack catalyst;

    public barObject(ItemStack var1, LiquidStack var2, ItemStack var3)
    {
        this.bar = var1;
        this.liquid = var2;
        this.catalyst = var3;
    }

    public ItemStack getItemStack()
    {
        return this.bar;
    }

    public LiquidStack getLiquidStack()
    {
        return this.liquid;
    }

    public ItemStack getCatalyst()
    {
        return this.catalyst;
    }
}
