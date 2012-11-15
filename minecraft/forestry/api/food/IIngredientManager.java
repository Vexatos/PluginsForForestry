package forestry.api.food;

import net.minecraft.src.ItemStack;

public interface IIngredientManager
{
    String getDescription(ItemStack var1);

    void addIngredient(ItemStack var1, String var2);
}
