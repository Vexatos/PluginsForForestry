package net.minecraft.src.denoflionsx.plugins.Forestry;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class tubes {
    
    // Electron tubes don't seem to be accessable via ItemInterface.
    // This will give me access.
    
    public static final Item tube = denLib.getItem("forestry.core.config.ForestryItem","tubes");
    public static ItemStack copperTube = new ItemStack(tube,1,0);
    public static ItemStack tinTube = new ItemStack(tube,1,1);
    public static ItemStack bronzeTube = new ItemStack(tube,1,2);
    public static ItemStack ironTube = new ItemStack(tube,1,3);
    public static ItemStack goldTube = new ItemStack(tube,1,4);
    public static ItemStack diamondTube = new ItemStack(tube,1,5);
    
}
