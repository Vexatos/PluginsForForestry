package forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class EngineBronzeFuel
{
    public final ItemStack liquid;
    public final int powerPerCycle;
    public final int burnDuration;
    public final int dissipationMultiplier;

    public EngineBronzeFuel(ItemStack var1, int var2, int var3, int var4)
    {
        this.liquid = var1;
        this.powerPerCycle = var2;
        this.burnDuration = var3;
        this.dissipationMultiplier = var4;
    }
}
