package denoflionsx.items.Containers;

import denoflionsx.API.PfFManagers;
import forestry.api.core.ItemInterface;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;

public class InfusionBar {
    
    private static ItemStack bar = PfFManagers.ItemManager.getItem("infusionbar");
    
    public static void recipe(){
        FMLWrapper.MODE.FML.addRecipe(bar,new Object[]{
                "XXX",
                "XXX",
                "XXX",
                Character.valueOf('X'),ItemInterface.getItem("ash")
        });
    }
    
}
