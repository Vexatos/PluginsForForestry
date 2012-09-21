package denoflionsx.plugins;

import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Railcraft.Modules.OreCrushModule.OreCrushmodule;
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
        if (!loaded){
            OreCrushmodule.load(this);
        }
        super.register();
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
        PfFManagers.ExtractorTargetManager.addItemStack(ItemRegistry.getItem("machine.beta.tank.iron.gauge", 1));
        return this.hooked;
    }

    @Override
    protected void recipes() {
        if (this.config.getOptionBool("CreosoteOilInWoodenBucket")){
            PfFManagers.ContainerManager.addLiquid("Creosote Oil", this.get("Creosote"), PfFManagers.ColorManager.getColor(Colors.Values.OIL.toString()));
        }
    }
}
