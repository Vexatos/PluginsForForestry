package denoflionsx.plugins;

import java.util.HashMap;
import denoflionsx.denLib.denLib;
import denoflionsx.core.core;

public class PluginRegistry {

    /*
     * This is the core file for plugins. plugins are managed here.
     * registerPlugins is called by the main core.
     */
    public static final boolean isRelease = false;
    public static final boolean debugmode = false;
    public static HashMap<String, pluginBase> plugins = new HashMap();

    public static void registerLatePlugins() {
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginRedPower_Enabled"))) {
            plugins.put("RedPowerWorld", new pluginRedPowerWorld());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginEE_Enabled"))) {
            plugins.put("EE", new pluginEE());
        }
        if (core.PfFCore.config.getOptionBool("pluginRailcraft_Enabled")) {
            plugins.put("Railcraft", new pluginRailcraft());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("UpdateCheck"))) {
            plugins.put("Updater", new pluginUpdater());
        }
    }

    public static void registerEarlyPlugins() {
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginBetterFarming_Enabled"))) {
            plugins.put("BetterFarming", new pluginBetterFarming());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginMineFactoryReloaded_Enabled"))) {
            plugins.put("MFR", new pluginMineFactoryReloaded());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginForestry_Enabled"))) {
            plugins.put("Forestry", new pluginForestry());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginIc2_Enabled"))) {
            plugins.put("IC2", new pluginIC2());
        }
        if (denLib.convertToBoolean(core.PfFCore.config.getOption("pluginBuildcraft_Enabled"))) {
            plugins.put("Buildcraft", new pluginBuildcraft());
        }
        if (core.PfFCore.config.getOptionBool("pluginFarmCraftory_Enabled")) {
            plugins.put("FarmCraftory", new pluginFarmCraftory());
        }
        plugins.put("CoreItems", new pluginCoreItems());
        if (core.PfFCore.config.getOptionBool("pluginBlueFood_Enabled")) {
            plugins.put("BluesFood", new pluginBlueFood());
        }
        plugins.put("Pams", new pluginPam());
    }

    public static boolean isPluginAlive(String name) {
        boolean loaded = false;
        if (plugins.get(name) != null) {
            loaded = plugins.get(name).loaded;
        }
        return loaded;
    }
}
