package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.Forestry.RefineryHack;
import net.minecraft.server.denoflionsx.plugins.Forestry.addFermenterRecipes;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.blueswaxModule;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.peatModule;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.solidfuelModule;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.stillModule;
import forestry.api.core.ItemInterface;
import net.minecraft.server.Item;

public class pluginForestry extends pluginBase
{
    public pluginForestry()
    {
        this.mod = "mod_Forestry";
        this.name = "pluginForestry";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            return this.hooked;
        }
        else
        {
            try
            {
                if (denLib.detect("mod_BuildCraftSilicon"))
                {
                    this.config.setOption("BiomassInRefinery", "false");
                }

                if (denLib.convertToBoolean(this.config.getOption("BiomassInRefinery")))
                {
                    RefineryHack.engage();
                }

                this.registerModules();
                this.hooked = true;
            }
            catch (Exception var5)
            {
                var5.printStackTrace();
                this.hooked = false;
            }
            finally
            {
                if (this.hooked)
                {
                    core.print(this.getName() + " Loaded!");
                }
            }

            return this.hooked;
        }
    }

    protected void recipes()
    {
        addFermenterRecipes.addItem(Item.SUGAR, 200, this);
        addFermenterRecipes.add(ItemInterface.getItem("liquidSeedOil"), 1.5F);
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();
            stillModule.load(this);
            peatModule.load(this);
            blueswaxModule.load(this);
            solidfuelModule.load(this);
            this.runConfig();

            if (this.loaded = this.init())
            {
                this.recipes();
            }
        }
    }

    protected void defaults()
    {
        this.config.addDefault("[Forestry Options]");
        this.config.addDefault("BiofuelInBiogas=true");
        this.config.addDefault("BiofuelMJt=5");
        this.config.addDefault("BiofuelBurnTime=40000");
        this.config.addDefault("# These options are for the Buildcraft Refinery");
        this.config.addDefault("# THIS OPTION WILL DISABLE OIL IN THE REFINERY UNLESS YOU INSTALL MY REFINERY PATCH!");
        this.config.addDefault("BiomassInRefinery=false");
        this.config.addDefault("BiomassPerBiofuel=2");
    }
}
