package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.peatModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.RefineryHack;
import net.minecraft.src.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.*;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuel.solidfuelModule;

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
        if (denLib.convertToBoolean(this.config.getOption("StillFix"))) {
            RecipeManagers.stillManager.addRecipe(Integer.valueOf(this.config.getOption("Still_WorkCycles")), new LiquidStack(ItemInterface.getItem("liquidBiomass").getItem(), Integer.valueOf(this.config.getOption("Still_BiomassPerCast"))), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), Integer.valueOf(this.config.getOption("Still_BiofuelPerCast"))));
        }
        if (denLib.convertToBoolean(this.config.getOption("BiofuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(ItemInterface.getItem("liquidBiofuel").itemID), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.config.getOption("BiofuelMJt")), Integer.valueOf(this.config.getOption("BiofuelBurnTime")), 1));
        }
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            stillModule.load(this);
            peatModule.load(this);
            extraFuelsModule.load(this);
            solidfuelModule.load(this);
            blueswaxModule.load(this);
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
