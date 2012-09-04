package denoflionsx.items.Containers;

import forestry.api.core.ItemInterface;
import net.minecraft.src.ItemStack;
import denoflionsx.API.API;
import denoflionsx.core.FMLWrapper;

public class InfusionBar {
    
    private static ItemStack bar = API.getItem("infusionbar");
    
    public static void recipe(){
        FMLWrapper.MODE.FML.addRecipe(bar,new Object[]{
                "XXX",
                "XXX",
                "XXX",
                Character.valueOf('X'),ItemInterface.getItem("ash")
        });
    }
    
}
