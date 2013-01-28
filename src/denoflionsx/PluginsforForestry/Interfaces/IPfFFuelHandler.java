package denoflionsx.PluginsforForestry.Interfaces;

import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public interface IPfFFuelHandler extends IFuelHandler{
    
    public void registerFuel(ItemStack fuel, int burn);
    
}
