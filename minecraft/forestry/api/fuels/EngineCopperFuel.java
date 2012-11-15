package forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class EngineCopperFuel
{
    public final ItemStack fuel;
    public final int powerPerCycle;
    public final int burnDuration;

    public EngineCopperFuel(ItemStack var1, int var2, int var3)
    {
        this.fuel = var1;
        this.powerPerCycle = var2;
        this.burnDuration = var3;
    }
}
