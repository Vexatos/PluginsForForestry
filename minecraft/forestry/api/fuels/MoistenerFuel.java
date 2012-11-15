package forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class MoistenerFuel
{
    public final ItemStack item;
    public final ItemStack product;
    public final int moistenerValue;
    public final int stage;

    public MoistenerFuel(ItemStack var1, ItemStack var2, int var3, int var4)
    {
        this.item = var1;
        this.product = var2;
        this.stage = var3;
        this.moistenerValue = var4;
    }
}
