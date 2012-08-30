package net.minecraft.src.denoflionsx.items.Containers;

import forestry.api.core.ItemInterface;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.API.API;

public class InfusionBar {
    
    private static ItemStack bar = API.getItem("infusionbar");
    
    public static void recipe(){
        ModLoader.addRecipe(bar,new Object[]{
                "XXX",
                "XXX",
                "XXX",
                Character.valueOf('X'),ItemInterface.getItem("ash")
        });
    }
    
}
