package forestry.api.recipes;

import net.minecraft.src.ItemStack;

public interface IMoistenerManager extends ICraftingProvider
{
    void addRecipe(ItemStack var1, ItemStack var2, int var3);
}
