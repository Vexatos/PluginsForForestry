package buildcraft.api.gates;

import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public interface ITriggerParameter
{
    ItemStack getItemStack();

    void set(ItemStack var1);

    void writeToNBT(NBTTagCompound var1);

    void readFromNBT(NBTTagCompound var1);

    ItemStack getItem();
}
