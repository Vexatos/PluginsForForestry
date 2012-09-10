package denoflionsx.core;

import cpw.mods.fml.common.IFuelHandler;
import java.util.HashMap;
import net.minecraft.src.ItemStack;

public class FuelHandler implements IFuelHandler{
    
    public static HashMap<Integer,Integer> values = new HashMap();

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (values.get(fuel.itemID) != null){
            return values.get(fuel.itemID);
        }else{
            return 0;
        }
    }
}
