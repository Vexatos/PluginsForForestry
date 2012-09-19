package denoflionsx.items.Containers;

import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.core.ItemInterface;

public class InfusionBar {
    
    private static ItemStack bar = PfFManagers.ItemManager.getItem("infusionbar");
    
    public static void recipe(){
        FMLWrapper.MODE.FML.addShapelessRecipe(bar, new Object[]{ItemInterface.getItem("ash"),ItemInterface.getItem("ash")});
    }
    
}
