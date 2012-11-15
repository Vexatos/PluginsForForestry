package forestry.api.recipes;

import net.minecraft.src.ItemStack;

public interface IGenericCrate
{
    void setContained(ItemStack var1, ItemStack var2);

    ItemStack getContained(ItemStack var1);
}
