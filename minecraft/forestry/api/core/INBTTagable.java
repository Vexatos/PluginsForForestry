package forestry.api.core;

import net.minecraft.src.NBTTagCompound;

public interface INBTTagable {
	void readFromNBT(NBTTagCompound nbttagcompound);

	void writeToNBT(NBTTagCompound nbttagcompound);
}
