package forestry.api.fuels;

import net.minecraft.src.ItemStack;

public class RainSubstrate
{
    public ItemStack item;
    public int duration;
    public float speed;
    public boolean reverse;

    public RainSubstrate(ItemStack var1, int var2, float var3)
    {
        this(var1, var2, var3, false);
    }

    public RainSubstrate(ItemStack var1, float var2)
    {
        this(var1, 0, var2, true);
    }

    public RainSubstrate(ItemStack var1, int var2, float var3, boolean var4)
    {
        this.item = var1;
        this.duration = var2;
        this.speed = var3;
        this.reverse = var4;
    }
}
