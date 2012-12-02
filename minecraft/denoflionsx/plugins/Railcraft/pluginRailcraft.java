package denoflionsx.plugins.Railcraft;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.PfFEvents;
import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.core.core;
import denoflionsx.plugins.Railcraft.Event.BoilerAccess;
import denoflionsx.plugins.Railcraft.Modules.OreCrushModule.OreCrushmodule;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import railcraft.common.api.core.items.ItemRegistry;

public class pluginRailcraft extends PfFPluginTemplate {

    public OreCrushmodule OreCrushModule = new OreCrushmodule("OreCrushModule", this.getName());
    public static ItemStack creosote;

    public pluginRailcraft(String name, String parent) {
        super(name, parent);
        PfFEvents.specialEvent.register(this);
    }

    @Override
    public void register() {
        super.register();
    }

    @Override
    public void defaults() {
        this.config.addDefault("[Railcraft Config Options]");
        this.config.addDefault("CreosoteOilInWoodenBucket=" + "true");
        this.config.addDefault("ReEnable_CreosoteInBiogas=" + "true");
        this.config.addDefault("Creosote_MJt=4");
        this.config.addDefault("Creosote_Burntime=14000");
        this.config.addDefault("Rebalance_Biofuel_In_Boiler=" + "true");
        this.config.addDefault("Biofuel_Boiler_HeatValue=" + "32000");
        this.config.addDefault("LavaInLiquidFirebox=" + "true");
        this.config.addDefault("Lava_HeatValue=" + 2000);
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void barrel(EventSpecial event) {
        if (!event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            return;
        }
        if (!this.isLoaded()){
            return;
        }
        if (creosote == null){
            return;
        }
        if (this.config.getOptionBool("CreosoteOilInWoodenBucket")) {
            PfFManagers.ContainerManager.addLiquid("Creosote Oil", creosote, PfFManagers.ColorManager.getColor(Colors.Values.OIL.toString()));
        }
    }

    @Override
    public void onWorldLoaded() {
        super.onWorldLoaded();
        if (this.config.getOptionBool("Rebalance_Biofuel_In_Boiler")) {
            core.print("Replacing Railcraft Boiler Value for Biofuel. Original: " + BoilerAccess.getFuelValue(EnumForestryLiquids.BIOFUEL.getLiquid()) + " | Replacement: " + this.config.getOptionInt("Biofuel_Boiler_HeatValue"));
            BoilerAccess.AddFuelValue(EnumForestryLiquids.BIOFUEL.getLiquid(), this.config.getOptionInt("Biofuel_Boiler_HeatValue"));
        }
    }

    @Override
    public void doSetup() {
        creosote = ItemRegistry.getItem("liquid.creosote.liquid", 1);
    }

    @Override
    public void recipes() {
        if (this.config.getOptionBool("ReEnable_CreosoteInBiogas")) {
            FuelManager.bronzeEngineFuel.put(creosote.itemID, new EngineBronzeFuel(creosote, this.config.getOptionInt("Creosote_MJt"), this.config.getOptionInt("Creosote_Burntime"), 1));
        }
        if (this.config.getOptionBool("LavaInLiquidFirebox")){
            BoilerAccess.AddFuelValue(new ItemStack(Block.lavaStill), this.config.getOptionInt("Lava_HeatValue"));
        }
    }
}
