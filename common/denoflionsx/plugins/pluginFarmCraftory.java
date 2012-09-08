package denoflionsx.plugins;

import denoflionsx.core.EnumModIDs;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.FarmCraftory.Crops.Crop;
import denoflionsx.plugins.FarmCraftory.Crops.CropMulti;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.plugins.FarmCraftory.Crops.Tree;
import denoflionsx.plugins.FarmCraftory.cropHarvest;
import forestry.api.cultivation.CropProviders;

public class pluginFarmCraftory extends pluginBase {

    public pluginFarmCraftory() {
        this.name = "pluginFarmCraftory";
        this.mod = EnumModIDs.MODS.FARMCRAFTORY.getID();
        this.modid = this.mod;
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded) {
            this.defaults();
            this.runConfig();
            if (this.loaded = this.init()) {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[FarmCraftory Options]");
        this.config.addDefault("ForestryIntegration=" + "true");
        this.config.addDefault("# This makes the plants grow super fast in the farm machine.");
        this.config.addDefault("CheatMode=" + "false");
    }

    @Override
    protected boolean init() {
        if (!this.detect()) {
            return false;
        }
        cropHarvest.cheatMode = this.getOptionBool("CheatMode");
        return true;
    }

    @Override
    protected void recipes() {
        for (EnumCrops.SINGLE s : EnumCrops.SINGLE.values()){
            CropProviders.cerealCrops.add(new Crop(s));
        }
        for (EnumCrops.MULTI s : EnumCrops.MULTI.values()){
            CropProviders.cerealCrops.add(new CropMulti(s));
        }
        for (EnumCrops.TREE s : EnumCrops.TREE.values()){
            CropProviders.cerealCrops.add(new Tree(s));
        }
    }
}
