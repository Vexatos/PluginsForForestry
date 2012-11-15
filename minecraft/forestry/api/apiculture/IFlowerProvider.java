package forestry.api.apiculture;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IFlowerProvider
{
    boolean isAcceptedFlower(World var1, IBeeGenome var2, int var3, int var4, int var5);

    boolean growFlower(World var1, IBeeGenome var2, int var3, int var4, int var5);

    String getDescription();

    ItemStack[] affectProducts(World var1, IBeeGenome var2, int var3, int var4, int var5, ItemStack[] var6);

    ItemStack[] getItemStacks();
}
