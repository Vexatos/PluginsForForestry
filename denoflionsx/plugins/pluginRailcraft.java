package denoflionsx.plugins;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.EnumModIDs;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import railcraft.common.api.core.items.ItemRegistry;

public class pluginRailcraft extends pluginBase {

    public pluginRailcraft() {
        this.name = "pluginRailcraft";
        this.mod = EnumModIDs.MODS.RAILCRAFT.getID();
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
        this.config.addDefault("[Railcraft Config Options]");
        this.config.addDefault("CreosoteOilInWoodenBucket=" + "true");
    }

    @Override
    protected boolean init() {
        this.hooked = true;
        this.addItem("Creosote", ItemRegistry.getItem("liquid.creosote.liquid", 1));
        if (this.config.getOptionBool("CreosoteOilInWoodenBucket")){
            // Add Creosote oil to my ItemManager so that WoodenBucketFuel can query for it.
            PfFManagers.ItemManager.registerItem("creosoteoil", this.get("Creosote"));
        }
        PfFManagers.ExtractorTargetManager.addItemStack(ItemRegistry.getItem("machine.beta.tank.iron.gauge", 1));
        //ItemRegistry.printItemTags();
        return this.hooked;
    }

    @Override
    protected void recipes() {
    }
}
