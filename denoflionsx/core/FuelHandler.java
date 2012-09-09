package denoflionsx.core;

import cpw.mods.fml.common.IFuelHandler;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemStack;

public class FuelHandler implements IFuelHandler{

    @Override
    public int getBurnTime(ItemStack fuel) {
        return PfFManagers.SolidFuelManager.getFuelValue(fuel.itemID);
    }
}
