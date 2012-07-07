package net.minecraft.src.denoflionsx.plugins.Forestry;

import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC2.RefineryManager;
import forestry.api.core.ItemInterface;

public class RefineryHack {
    
    public static void engage(){
        
            RefineryManager r = new RefineryManager();
            r.addRecipe(new Integer[]{ItemInterface.getItem("liquidBiomass").itemID, Integer.valueOf(core.config.getOption("BiomassPerBiofuel")), 0, 0, 10, ItemInterface.getItem("liquidBiofuel").itemID, 1, 1});
        
    }
    
}
