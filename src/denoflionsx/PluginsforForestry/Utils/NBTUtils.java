package denoflionsx.PluginsforForestry.Utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class NBTUtils {

    public static void writeTanksToNBT(NBTTagCompound tag, LiquidTank[] tanks) {
        NBTTagCompound tankNBT = new NBTTagCompound();
        for (int i = 0; i < tanks.length; i++) {
            NBTTagCompound liquid = new NBTTagCompound();
            if (tanks[i].getLiquid() == null) {
                liquid.setInteger("ID", 0);
                liquid.setInteger("Meta", 0);
                liquid.setInteger("Amount", 0);
            } else {
                liquid.setInteger("ID", tanks[i].getLiquid().itemID);
                liquid.setInteger("Meta", tanks[i].getLiquid().itemMeta);
                liquid.setInteger("Amount", tanks[i].getLiquid().amount);
            }
            tankNBT.setTag("Tank" + i, liquid);
        }
        tag.setTag("Tanks", tankNBT);
    }

    public static LiquidTank[] loadTanksFromNBT(NBTTagCompound tag, LiquidTank[] tanks) {
        NBTTagCompound tankNBT = tag.getCompoundTag("Tanks");
        for (int i = 0; i < tanks.length; i++) {
            NBTTagCompound liquid = tankNBT.getCompoundTag("Tank" + i);
            int ID = liquid.getInteger("ID");
            int Meta = liquid.getInteger("Meta");
            int Amount = liquid.getInteger("Amount");
            if (ID != 0) {
                LiquidStack l = new LiquidStack(ID, Amount, Meta);
                tanks[i].setLiquid(l);
            }
        }
        return tanks;
    }
}
