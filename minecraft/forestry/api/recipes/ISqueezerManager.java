package forestry.api.recipes;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface ISqueezerManager extends ICraftingProvider
{
    void addRecipe(int var1, ItemStack[] var2, LiquidStack var3, ItemStack var4, int var5);

    void addRecipe(int var1, ItemStack[] var2, LiquidStack var3);
}
