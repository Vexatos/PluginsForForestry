package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.blueswaxModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.peatModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuelModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.RefineryHack;
import net.minecraft.src.denoflionsx.plugins.Forestry.StillHack;
import net.minecraft.src.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;

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
            core.print(mod + " not found!");
            return hooked;
        }
        try {
            if (denLib.detect("mod_BuildCraftSilicon")) {
                // If BC3 is detected forcefully disable the Refinery hack.
                config.setOption("BiomassInRefinery", "false");
            }     
            if (denLib.convertToBoolean(config.getOption("StillFix"))) {
                StillHack.engage();
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
        config.addDefault("# These options are for the Forestry Still");
        config.addDefault("# StillFix removes SirSengir's Still recipe that has a hardcoded 10 -> 3 ratio...");
        config.addDefault("# ...and substitutes my own with configurable ratio.");
        config.addDefault("# If StillFix is disabled the PerCast options are not used.");
        config.addDefault("StillFix=true");
        config.addDefault("Still_BiomassPerCast=10");
        config.addDefault("Still_BiofuelPerCast=5");
        config.addDefault("# This sets the still's speed. Mim value is 1.");
        config.addDefault("Still_WorkCycles=100");
        config.addDefault("BiofuelInBiogas=true");
        config.addDefault("BiofuelMJt=5");
        config.addDefault("BiofuelBurnTime=40000");
        config.addDefault("# These options are for the Buildcraft Refinery");
        config.addDefault("# THIS OPTION WILL DISABLE OIL IN THE REFINERY UNLESS YOU INSTALL MY REFINERY PATCH!");
        config.addDefault("BiomassInRefinery=false");
        config.addDefault("BiomassPerBiofuel=2");

    }
}
