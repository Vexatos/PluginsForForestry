package denoflionsx.plugins.Buildcraft;

import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;
import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;
import denoflionsx.plugins.Buildcraft.Modules.quarryModule;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import java.util.LinkedList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;

public class pluginBuildcraft extends PfFPluginTemplate implements ITriggerProvider {

    public quarryModule QuarryModule = new quarryModule("QuarryModule", this.getName());
    public milkModule MilkModule = new milkModule("MilkModule", this.getName());
    public static ItemStack fuel;
    public static ItemStack oil;

    public pluginBuildcraft(String name, String parent) {
        super(name, parent);
    }

    @Override
    public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
        
        return new LinkedList();
    }

    @Override
    public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
        return new LinkedList();
    }

    @Override
    public void recipes() {
        if (denLib.convertToBoolean(config.getOption("FuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(fuel.itemID), new EngineBronzeFuel(fuel, Integer.valueOf(config.getOption("FuelMJt")), Integer.valueOf(config.getOption("FuelBurnTime")), 1));
        }
        if (denLib.convertToBoolean(config.getOption("OilInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(oil.itemID), new EngineBronzeFuel(oil, Integer.valueOf(config.getOption("OilMJt")), Integer.valueOf(config.getOption("OilBurntime")), 1));
        }
        if (this.config.getOptionBool("WoodenBucketIntegration")) {
            PfFManagers.ContainerManager.addLiquid("Oil", oil, PfFManagers.ColorManager.getColor(Colors.Values.BLACK.toString()));
            PfFManagers.ContainerManager.addLiquid("Fuel", fuel, PfFManagers.ColorManager.getColor(Colors.Values.PISS.toString()));
        }
    }

    @Override
    public void doSetup() {
        String BCE = EnumModIDs.MODS.BUILDCRAFT_ENERGY.gettheClass();
        oil = denLib.ReflectionHelper.getNewItemStackBlock(BCE, "oilStill");
        fuel = denLib.ReflectionHelper.getNewItemStack(BCE, "fuel");
        
        ActionManager.registerTriggerProvider(this);
    }

    @Override
    public void defaults() {
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
