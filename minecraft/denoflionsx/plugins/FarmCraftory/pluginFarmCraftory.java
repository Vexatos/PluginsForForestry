package denoflionsx.plugins.FarmCraftory;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.plugins.FarmCraftory.Crops.Crop;
import denoflionsx.plugins.FarmCraftory.Crops.CropMulti;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.plugins.FarmCraftory.Crops.Tree;
import denoflionsx.plugins.FarmCraftory.Crops.cropHarvest;
import denoflionsx.plugins.FarmCraftory.Modules.Liquidmodule;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import forestry.api.cultivation.CropProviders;

public class pluginFarmCraftory extends PfFPluginTemplate {

    public Liquidmodule LiquidModule = new Liquidmodule("LiquidModule",this.getName());
    
    public pluginFarmCraftory(String name, String parent) {
        super(name, parent);
    }

   

    

    @Override
    public void defaults() {
        this.config.addDefault("[FarmCraftory Options]");
        this.config.addDefault("ForestryIntegration=" + "true");
        this.config.addDefault("#THIS OPTION BREAKS THE COMBINE IN TERRIBLE WAYS.");
        this.config.addDefault("TreeFruitHarvestByCombine=" + "false");
        this.config.addDefault("# This makes the plants grow super fast in the farm machine.");
        this.config.addDefault("CheatMode=" + "false");
        this.config.addDefault("Seeds_AmountPerSqueeze=" + 100);
    }

    

    @Override
    public void doSetup() {
        cropHarvest.cheatMode = this.config.getOptionBool("CheatMode");
    }

    
    @Override
    public void recipes() {
        int amount = this.config.getOptionInt("Seeds_AmountPerSqueeze");
        if (this.config.getOptionBool("ForestryIntegration")) {
            for (EnumCrops.SINGLE s : EnumCrops.SINGLE.values()) {
                CropProviders.cerealCrops.add(new Crop(s));
                SqueezerHelper.add(s.getPlant().getSeed(), new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, amount));
            }
            for (EnumCrops.MULTI s : EnumCrops.MULTI.values()) {
                CropProviders.cerealCrops.add(new CropMulti(s));
                SqueezerHelper.add(s.getPlant().getSeed(), new LiquidStack(EnumForestryLiquids.SEEDOIL.getLiquid().itemID, amount));
            }
            for (EnumCrops.TREE s : EnumCrops.TREE.values()) {
                CropProviders.poaleCrops.add(new Tree(s));
            }
        }
    }
}
