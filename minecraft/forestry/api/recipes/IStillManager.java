package forestry.api.recipes;

import net.minecraftforge.liquids.LiquidStack;

public interface IStillManager extends ICraftingProvider
{
    void addRecipe(int var1, LiquidStack var2, LiquidStack var3);
}
