package forestry.api.recipes;

import java.util.HashMap;
import net.minecraft.src.ItemStack;

public interface ICentrifugeManager extends ICraftingProvider
{
    void addRecipe(int var1, ItemStack var2, HashMap var3);

    void addRecipe(int var1, ItemStack var2, ItemStack[] var3, int[] var4);

    void addRecipe(int var1, ItemStack var2, ItemStack var3, ItemStack var4, int var5);

    void addRecipe(int var1, ItemStack var2, ItemStack var3);
}
