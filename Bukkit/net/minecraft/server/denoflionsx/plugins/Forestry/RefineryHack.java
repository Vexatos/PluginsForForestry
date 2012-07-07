package net.minecraft.server.denoflionsx.plugins.Forestry;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.BC2.RefineryManager;
import forestry.api.core.ItemInterface;

public class RefineryHack
{
    public static void engage()
    {
        RefineryManager var0 = new RefineryManager();
        var0.addRecipe(new Integer[] {Integer.valueOf(ItemInterface.getItem("liquidBiomass").id), Integer.valueOf(core.config.getOption("BiomassPerBiofuel")), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(10), Integer.valueOf(ItemInterface.getItem("liquidBiofuel").id), Integer.valueOf(1), Integer.valueOf(1)});
    }
}
