package denoflionsx.plugins;

import denoflionsx.plugins.Forestry.Modules.StillModule.stillModule;
import denoflionsx.plugins.Forestry.Modules.ExtraFuelsModule.extraFuelsModule;
import denoflionsx.plugins.Forestry.Modules.BlueWaxModule.BlueWaxmodule;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumForestryLiquids;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Forestry.Modules.PeatModule.peatModule;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.plugins.Forestry.Modules.SolidFuelModule.solidfuelModule;
import forestry.api.core.BlockInterface;

public class pluginForestry extends pluginBase {

    public pluginForestry() {
        this.mod = "Forestry";
        this.name = "pluginForestry";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return hooked;
        }
        this.registerModules();
        hooked = true;
        return hooked;
    }

    @Override
    protected void recipes() {
        if (true) {
            PfFManagers.FermenterManager.addItem(new ItemStack(Item.sugar), 200);
            PfFManagers.FermenterManager.registerPfFLiquid(EnumForestryLiquids.SEEDOIL.getLiquid(), 1.5f);
        }
        if (denLib.convertToBoolean(this.config.getOption("StillFix"))) {
            RecipeManagers.stillManager.addRecipe(Integer.valueOf(this.config.getOption("Still_WorkCycles")), new LiquidStack(ItemInterface.getItem("liquidBiomass").getItem(), Integer.valueOf(this.config.getOption("Still_BiomassPerCast"))), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), Integer.valueOf(this.config.getOption("Still_BiofuelPerCast"))));
        }
        if (denLib.convertToBoolean(this.config.getOption("BiofuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(EnumForestryLiquids.BIOFUEL.getLiquid().itemID), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.config.getOption("BiofuelMJt")), Integer.valueOf(this.config.getOption("BiofuelBurnTime")), 1));
        }
        if (this.config.getOptionBool("WoodenBucketIntegration")) {
            PfFManagers.ContainerManager.addLiquid("Seed Oil", EnumForestryLiquids.SEEDOIL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.SEEDOIL.toString()));
            PfFManagers.ContainerManager.addLiquid("Apple Juice", EnumForestryLiquids.APPLEJUICE.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.LIGHTGREEN.toString()));
            PfFManagers.ContainerManager.addLiquid("Honey", EnumForestryLiquids.HONEY.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.HONEY.toString()));
            PfFManagers.ContainerManager.addLiquid("Biomass", EnumForestryLiquids.BIOMASS.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.GREEN.toString()));
            PfFManagers.ContainerManager.addLiquid("Biofuel", EnumForestryLiquids.BIOFUEL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.ORANGE2.toString()));
        }
        for (int i = 0; i != 15; i++) {
            PfFManagers.ExtractorTargetManager.addBlock(BlockInterface.getBlock("glass").itemID, i);
        }
    }

    @Override
    public void register() {
        if (!loaded) {
            try {
                stillModule.load(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            peatModule.load(this);
            extraFuelsModule.load(this);
            solidfuelModule.load(this);
            BlueWaxmodule.load(this);
        }
        super.register();
    }

    @Override
    protected void defaults() {
        config.addDefault("[Forestry Options]");
        config.addDefault("BiofuelInBiogas=true");
        config.addDefault("BiofuelMJt=5");
        config.addDefault("BiofuelBurnTime=40000");
        config.addDefault("WoodenBucketIntegration=" + "true");
    }
}
