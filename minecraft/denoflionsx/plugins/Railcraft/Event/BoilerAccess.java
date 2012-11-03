package denoflionsx.plugins.Railcraft.Event;

import buildcraft.api.liquids.LiquidStack;
import net.minecraft.src.ItemStack;
import railcraft.common.api.fuel.FuelManager;

public class BoilerAccess {
    
    // The Boiler manager and the Forestry engine stuff have the same name.
    // This causes NetBeans to have a fit if you use them both in one class.
    // So this class fixes that.
    
    public static void AddFuelValue(ItemStack liquid, int value){
        FuelManager.addBoilerFuel(new LiquidStack(liquid.itemID,1000), value);
    }
    
    public static int getFuelValue(ItemStack liquid){
        return FuelManager.getBoilerFuelValue(new LiquidStack(liquid.itemID,1000));
    }
    
}
