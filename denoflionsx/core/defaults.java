package denoflionsx.core;

public class defaults {

    public static void setup() {


        core.PfFCore.config.addDefault("[PluginsForForestry Config File]");
        core.PfFCore.config.addDefault("pluginForestry_Enabled=true");
        core.PfFCore.config.addDefault("pluginBetterFarming_Enabled=true");
        core.PfFCore.config.addDefault("pluginBuildcraft_Enabled=true");
        core.PfFCore.config.addDefault("pluginMineFactoryReloaded_Enabled=true");
        core.PfFCore.config.addDefault("pluginAdvancedPowerSystems_Enabled=true");
        core.PfFCore.config.addDefault("pluginIc2_Enabled=true");
        core.PfFCore.config.addDefault("pluginEE_Enabled=true");
        core.PfFCore.config.addDefault("pluginRedPower_Enabled=true");
        core.PfFCore.config.addDefault("pluginFarmCraftory_Enabled=true");
        core.PfFCore.config.addDefault("pluginRailcraft_Enabled=true");
        core.PfFCore.config.addDefault("pluginBlueFood_Enabled=true");
        core.PfFCore.config.addDefault("EnableBC2TankIntegration=false");
        core.PfFCore.config.addDefault("Verbose=true");
        core.PfFCore.config.addDefault("UpdateCheck=true");
        core.PfFCore.config.writeConfig();
    }
}
