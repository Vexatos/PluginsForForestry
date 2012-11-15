package forestry.api.recipes;

import net.minecraft.src.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public interface IBottlerManager extends ICraftingProvider
{
    void addRecipe(int var1, LiquidStack var2, ItemStack var3, ItemStack var4);
}
