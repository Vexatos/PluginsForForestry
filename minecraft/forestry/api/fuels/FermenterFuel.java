package forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class FermenterFuel
{
    public final ItemStack item;
    public final int fermentPerCycle;
    public final int burnDuration;

    public FermenterFuel(ItemStack var1, int var2, int var3)
    {
        this.item = var1;
        this.fermentPerCycle = var2;
        this.burnDuration = var3;
    }
}
