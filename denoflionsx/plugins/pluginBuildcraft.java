package denoflionsx.plugins;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.EnumModIDs;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Buildcraft.Modules.quarryModule;
import denoflionsx.plugins.Buildcraft.TankManager;
import denoflionsx.plugins.Buildcraft.goldGear;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;

public class pluginBuildcraft extends pluginBase {

    public pluginBuildcraft() {

        this.name = "pluginBuildcraft";
        this.mod = EnumModIDs.MODS.BUILDCRAFT_CORE.getID();
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            milkModule.load(this);
            quarryModule.load(this);
           // LavaFurnacemodule.load(this);
            this.runConfig();
            if (loaded = init()) {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    @Override
    protected boolean detect() {
        return denLib.detect(this.mod) && denLib.detect(EnumModIDs.MODS.BUILDCRAFT_TRANSPORT.getID());
    }

    @Override
    protected void recipes() {
        if (denLib.convertToBoolean(config.getOption("FuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(this.items.get("Fuel").itemID), new EngineBronzeFuel(this.items.get("Fuel"), Integer.valueOf(config.getOption("FuelMJt")), Integer.valueOf(config.getOption("FuelBurnTime")), 1));
        }
        if (denLib.convertToBoolean(config.getOption("OilInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(this.blocks.get("Oil").itemID), new EngineBronzeFuel(this.blocks.get("Oil"), Integer.valueOf(config.getOption("OilMJt")), Integer.valueOf(config.getOption("FuelBurnTime")), 1));
        }

        this.registerModules();

    }

    @Override
    protected boolean init() {
        if (!detect()){
            return false;
        }
        TankManager.setup();
        goldGear.setup();
        String BCE = EnumModIDs.MODS.BUILDCRAFT_ENERGY.gettheClass();
        if (core.isBukkit) {
            
        }
        this.addBlock(BCE, "oilStill", "Oil", 0);
        this.addItem(BCE, "fuel", "Fuel", 0);
        if (this.config.getOptionBool("WoodenBucketIntegration")){
            PfFManagers.ItemManager.registerItem("oil",this.getBlockItemStack("Oil"));
            PfFManagers.ItemManager.registerItem("fuel",this.get("Fuel"));
        }
        this.hooked = true;
        return this.hooked;
    }

    @Override
    protected void defaults() {
        config.addDefault("[Buildcraft Options]");
        config.addDefault("FuelInBiogas=true");
        config.addDefault("FuelMJt=5");
        config.addDefault("FuelBurnTime=50000");
        config.addDefault("OilInBiogas=true");
        config.addDefault("OilMJt=2");
        config.addDefault("OilBurntime=10000");
        config.addDefault("WoodenBucketIntegration=" + "true");

    }
}
