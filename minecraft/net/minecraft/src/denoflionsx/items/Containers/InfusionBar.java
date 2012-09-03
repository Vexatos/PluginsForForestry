package net.minecraft.src.denoflionsx.items.Containers;

import forestry.api.core.ItemInterface;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.Crafting;

public class InfusionBar {
    
    private static ItemStack bar = API.getItem("infusionbar");
    
    public static void recipe(){
        Crafting.MODE.FML.addRecipe(bar,new Object[]{
                "XXX",
                "XXX",
                "XXX",
                Character.valueOf('X'),ItemInterface.getItem("ash")
        });
    }
    
}
