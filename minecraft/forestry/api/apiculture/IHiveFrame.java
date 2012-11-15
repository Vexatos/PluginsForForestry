package forestry.api.apiculture;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IHiveFrame extends IBeeModifier
{
    ItemStack frameUsed(World var1, ItemStack var2, IBee var3, int var4);
}
