package denoflionsx.plugins.Railcraft;

import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.plugins.Railcraft.Modules.OreCrushModule.OreCrushmodule;
import net.minecraft.src.ItemStack;
import railcraft.common.api.core.items.ItemRegistry;

public class pluginRailcraft extends PfFPluginTemplate {
    
    public OreCrushmodule OreCrushModule = new OreCrushmodule("OreCrushModule",this.getName());
    
    public static ItemStack creosote = ItemRegistry.getItem("liquid.creosote.liquid", 1);

    public pluginRailcraft(String name, String parent) {
        super(name, parent);
    }


    @Override
    public void register() {
        super.register();
    }

    @Override
    public void defaults() {
        this.config.addDefault("[Railcraft Config Options]");
        this.config.addDefault("CreosoteOilInWoodenBucket=" + "true");
    }

 

    @Override
    public void onWorldLoaded() {
        
        super.onWorldLoaded();
    }

    @Override
    public void doSetup() {
        PfFManagers.ExtractorTargetManager.addItemStack(ItemRegistry.getItem("machine.beta.tank.iron.gauge", 1));
    }

 

    @Override
    public void recipes() {
        if (this.config.getOptionBool("CreosoteOilInWoodenBucket")) {
            PfFManagers.ContainerManager.addLiquid("Creosote Oil", creosote, PfFManagers.ColorManager.getColor(Colors.Values.OIL.toString()));
        }
    }
}
