package denoflionsx.items.Containers;

import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.core.ItemInterface;
import net.minecraft.src.Item;

public class InfusionBar {
    
    private static ItemStack bar = PfFManagers.ItemManager.getItem("infusionbar");
    
    public static void recipe(){
        FMLWrapper.MODE.FML.addRecipe(bar, new Object[]{
                "PPP",
                "PAP",
                "PPP",
                Character.valueOf('A'),ItemInterface.getItem("ash"),
                Character.valueOf('P'),Item.paper});
    }
    
}
