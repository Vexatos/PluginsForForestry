package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.blueswaxModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.peatModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuelModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.RefineryHack;
import net.minecraft.src.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.stillModule;

public class pluginForestry extends pluginBase {

    public pluginForestry() {
        this.mod = "mod_Forestry";
        this.name = "pluginForestry";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return hooked;
        }
        try {
            if (denLib.detect("mod_BuildCraftSilicon")) {
                // If BC3 is detected forcefully disable the Refinery hack.
                config.setOption("BiomassInRefinery", "false");
            }     
            if (denLib.convertToBoolean(config.getOption("BiomassInRefinery"))) {
                RefineryHack.engage();
            }
            this.registerModules();
            hooked = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            hooked = false;
        } finally {
            if (hooked) {
                core.print(getName() + " Loaded!");
            }
        }
        return hooked;
    }

    @Override
    protected void recipes() {
        if (true) {
            addFermenterRecipes.addItem(Item.sugar, 200, this);
            addFermenterRecipes.add(ItemInterface.getItem("liquidSeedOil"), 1.5F);
        }
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            stillModule.load(this);
            peatModule.load(this);
            blueswaxModule.load(this);
            solidfuelModule.load(this);
            this.runConfig();
            if (loaded = init()) {
                recipes();
            }
        }
    }

    @Override
    protected void defaults() {
        config.addDefault("[Forestry Options]");
        config.addDefault("BiofuelInBiogas=true");
        config.addDefault("BiofuelMJt=5");
        config.addDefault("BiofuelBurnTime=40000");
        config.addDefault("# These options are for the Buildcraft Refinery");
        config.addDefault("# THIS OPTION WILL DISABLE OIL IN THE REFINERY UNLESS YOU INSTALL MY REFINERY PATCH!");
        config.addDefault("BiomassInRefinery=false");
        config.addDefault("BiomassPerBiofuel=2");

    }
}
