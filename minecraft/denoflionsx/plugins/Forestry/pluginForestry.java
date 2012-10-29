package denoflionsx.plugins.Forestry;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.core.PfFPluginTemplate;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Forestry.Modules.BlueWaxModule.BlueWaxmodule;
import denoflionsx.plugins.Forestry.Modules.ExtraFuelsModule.extraFuelsModule;
import denoflionsx.plugins.Forestry.Modules.PeatModule.peatModule;
import denoflionsx.plugins.Forestry.Modules.SolidFuelModule.solidfuelModule;
import denoflionsx.plugins.Forestry.Modules.StillModule.stillModule;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.core.BlockInterface;

public class pluginForestry extends PfFPluginTemplate {

    public peatModule PeatModule = new peatModule("PeatModule", this.getName());
    public extraFuelsModule ExtraFuelsModule = new extraFuelsModule("ExtraFuelsModule", this.getName());
    public solidfuelModule SolidFuelModule = new solidfuelModule("SolidFuelModule", this.getName());
    public stillModule StillModule = new stillModule("StillModule", this.getName());
    public BlueWaxmodule BlueWaxModule = new BlueWaxmodule("BlueWaxModule", this.getName());

    public pluginForestry(String name, String parent) {
        super(name, parent);
        PfFEvents.specialEvent.register(this);
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void barrel(EventSpecial event) {
        if (!event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            return;
        }
        if (this.config.getOptionBool("WoodenBucketIntegration")) {
            PfFManagers.ContainerManager.addLiquid("Seed Oil", EnumForestryLiquids.SEEDOIL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.SEEDOIL.toString()));
            PfFManagers.ContainerManager.addLiquid("Apple Juice", EnumForestryLiquids.APPLEJUICE.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.LIGHTGREEN.toString()));
            PfFManagers.ContainerManager.addLiquid("Honey", EnumForestryLiquids.HONEY.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.HONEY.toString()));
            PfFManagers.ContainerManager.addLiquid("Biomass", EnumForestryLiquids.BIOMASS.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.GREEN.toString()));
            PfFManagers.ContainerManager.addLiquid("Biofuel", EnumForestryLiquids.BIOFUEL.getLiquid(), PfFManagers.ColorManager.getColor(Colors.Values.ORANGE2.toString()));
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
