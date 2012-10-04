package denoflionsx.plugins.Forestry;

import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.core.IPfFPluginTemplate;
import denoflionsx.core.core;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Forestry.Modules.ExtraFuelsModule.extraFuelsModule;
import denoflionsx.plugins.Forestry.Modules.PeatModule.peatModule;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.core.BlockInterface;

public class pluginForestry extends IPfFPluginTemplate {
    
    public peatModule PeatModule = PeatModule = new peatModule("PeatModule",this.getName());
    public extraFuelsModule ExtraFuelsModule = new extraFuelsModule("ExtraFuelsModule",this.getName());

    public pluginForestry(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void doSetup() {
        
    }
    
    @Override
    public void itemInitialized(EventItemInitialized event) {
        if (event.getName().equals("barrelfuels")) {
            if (this.config.getOptionBool("WoodenBucketIntegration")) {
                core.print("Setting up Forestry Liquids with Wooden Buckets.");
                PfFManagers.ContainerManager.addLiquid("Seed Oil", EnumForestryLiquids.SEEDOIL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.SEEDOIL.toString()));
                PfFManagers.ContainerManager.addLiquid("Apple Juice", EnumForestryLiquids.APPLEJUICE.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.LIGHTGREEN.toString()));
                PfFManagers.ContainerManager.addLiquid("Honey", EnumForestryLiquids.HONEY.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.HONEY.toString()));
                PfFManagers.ContainerManager.addLiquid("Biomass", EnumForestryLiquids.BIOMASS.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.GREEN.toString()));
                PfFManagers.ContainerManager.addLiquid("Biofuel", EnumForestryLiquids.BIOFUEL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.ORANGE2.toString()));
            }
        }
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
        if (event.getPlugin().getName().equals(("Loader"))){
            this.register();
        }
    }
    
    @Override
    public void recipes() {
        if (true) {
            PfFManagers.FermenterManager.addItem(new ItemStack(Item.sugar), 200);
            PfFManagers.FermenterManager.registerPfFLiquid(EnumForestryLiquids.SEEDOIL.getLiquid(), 1.5f);
        }
        if (denLib.convertToBoolean(this.config.getOption("BiofuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(EnumForestryLiquids.BIOFUEL.getLiquid().itemID), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.config.getOption("BiofuelMJt")), Integer.valueOf(this.config.getOption("BiofuelBurnTime")), 1));
        }
        for (int i = 0; i != 15; i++) {
            PfFManagers.ExtractorTargetManager.addBlock(BlockInterface.getBlock("glass").itemID, i);
        }
    }

    @Override
    public void defaults() {
        config.addDefault("[Forestry Options]");
        config.addDefault("BiofuelInBiogas=true");
        config.addDefault("BiofuelMJt=5");
        config.addDefault("BiofuelBurnTime=40000");
        config.addDefault("WoodenBucketIntegration=" + "true");
    }
}
