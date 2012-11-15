package forestry.api.recipes;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface IFabricatorManager extends ICraftingProvider
{
    void addRecipe(ItemStack var1, LiquidStack var2, ItemStack var3, Object[] var4);

    void addSmelting(ItemStack var1, LiquidStack var2, int var3);
}
