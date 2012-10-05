package denoflionsx.plugins.Buildcraft;

import denoflionsx.Enums.Colors;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;
import denoflionsx.plugins.Buildcraft.Modules.quarryModule;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.src.ItemStack;

public class pluginBuildcraft extends PfFPluginTemplate {
    
    public quarryModule QuarryModule = new quarryModule("QuarryModule",this.getName());
    public milkModule MilkModule = new milkModule("MilkModule",this.getName());
    
    public static ItemStack fuel;
    public static ItemStack oil;

    public pluginBuildcraft(String name, String parent) {
        super(name, parent);
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
            PfFManagers.ContainerManager.addLiquid("Oil",oil, PfFManagers.ColorManager.getColor(Colors.Values.BLACK.toString()));
            PfFManagers.ContainerManager.addLiquid("Fuel",fuel, PfFManagers.ColorManager.getColor(Colors.Values.PISS.toString()));
        }
    }

    @Override
    public void doSetup() {
        String BCE = EnumModIDs.MODS.BUILDCRAFT_ENERGY.gettheClass();
        oil = denLib.ReflectionHelper.getNewItemStackBlock(BCE, "oilStill");
        fuel = denLib.ReflectionHelper.getNewItemStack(BCE, "fuel");
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
