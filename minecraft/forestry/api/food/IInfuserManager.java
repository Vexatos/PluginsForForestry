package forestry.api.food;

import net.minecraft.src.ItemStack;

public interface IInfuserManager
{
    void addMixture(int var1, ItemStack var2, IBeverageEffect var3);

    void addMixture(int var1, ItemStack[] var2, IBeverageEffect var3);

    ItemStack getSeasoned(ItemStack var1, ItemStack[] var2);

    boolean hasMixtures(ItemStack[] var1);

    ItemStack[] getRequired(ItemStack[] var1);
}
