package buildcraft.api.inventory;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public interface ISpecialInventory extends IInventory
{
    int addItem(ItemStack var1, boolean var2, ForgeDirection var3);

    ItemStack[] extractItem(boolean var1, ForgeDirection var2, int var3);
}
