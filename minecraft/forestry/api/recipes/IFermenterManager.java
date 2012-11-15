package forestry.api.recipes;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface IFermenterManager extends ICraftingProvider
{
    void addRecipe(ItemStack var1, int var2, float var3, LiquidStack var4, LiquidStack var5);

    void addRecipe(ItemStack var1, int var2, float var3, LiquidStack var4);
}
