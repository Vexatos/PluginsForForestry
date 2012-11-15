package forestry.api.recipes;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface ICarpenterManager extends ICraftingProvider
{
    void addRecipe(ItemStack var1, ItemStack var2, Object[] var3);

    void addRecipe(int var1, ItemStack var2, ItemStack var3, Object[] var4);

    void addRecipe(int var1, LiquidStack var2, ItemStack var3, ItemStack var4, Object[] var5);

    void addCrating(String var1, ItemStack var2, ItemStack var3);

    void addCrating(ItemStack var1);
}
