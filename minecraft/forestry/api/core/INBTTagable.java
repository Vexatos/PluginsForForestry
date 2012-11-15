package forestry.api.core;

import net.minecraft.src.NBTTagCompound;

public interface INBTTagable
{
    void readFromNBT(NBTTagCompound var1);

    void writeToNBT(NBTTagCompound var1);
}
