package net.minecraft.server.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.server.Item;

public class stillModule extends baseModule
{
    private Item biomass = ItemInterface.getItem("liquidBiomass").getItem();
    private Item biofuel = ItemInterface.getItem("liquidBiofuel").getItem();

    public stillModule(pluginBase var1)
    {
        super(var1);
    }

    public static void load(pluginBase var0)
    {
        stillModule var1 = new stillModule(var0);
        var1.register();
    }

    protected void defaults()
    {
        this.addDefault("# These options are for the Forestry Still");
        this.addDefault("# StillFix removes SirSengir\'s Still recipe that has a hardcoded 10 -> 3 ratio...");
        this.addDefault("# ...and substitutes my own with configurable ratio.");
        this.addDefault("# If StillFix is disabled the PerCast options are not used.");
        this.addDefault("StillFix=true");
        this.addDefault("Still_BiomassPerCast=10");
        this.addDefault("Still_BiofuelPerCast=5");
        this.addDefault("# This sets the still\'s speed. Mim value is 1.");
        this.addDefault("Still_WorkCycles=100");
    }

    protected void init()
    {
        if (this.getOptionBool("StillFix"))
        {
            String var1 = "MachineStill$RecipeManager";
            String var2 = "forestry.factory.";
            String var3 = "recipes";

            try
            {
                Class var4 = Class.forName(var2 + var1);
                Field var5 = var4.getField(var3);
                var5.setAccessible(true);
                ArrayList var6 = (ArrayList)var5.get((Object)null);
                var6.remove(0);
                var5.set((Object)null, var6);
            }
            catch (Exception var7)
            {
                var7.printStackTrace();
            }

            this.recipes();
        }
    }

    protected void recipes()
    {
        RecipeManagers.stillManager.addRecipe(this.getOptionInt("Still_WorkCycles").intValue(), new LiquidStack(this.biomass, this.getOptionInt("Still_BiomassPerCast").intValue()), new LiquidStack(this.biofuel, this.getOptionInt("Still_BiofuelPerCast").intValue()));
    }
}
