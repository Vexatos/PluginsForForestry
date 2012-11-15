package buildcraft.api.transport;

import net.minecraft.src.NBTTagCompound;

public interface IPassiveItemContribution
{
    void readFromNBT(NBTTagCompound var1);

    void writeToNBT(NBTTagCompound var1);
}
