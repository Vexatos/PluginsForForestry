package denoflionsx.plugins;

import java.util.HashMap;
import denoflionsx.denLib.denLib;
import denoflionsx.core.core;

public class pluginCore {

    /*
     * This is the core file for plugins. plugins are managed here.
     * registerPlugins is called by the main core.
     */
    public static final boolean isRelease = false;
    public static final boolean debugmode = false;
    public static HashMap<String, pluginBase> plugins = new HashMap();

    public static void registerLatePlugins() {
        if (denLib.convertToBoolean(core.config.getOption("pluginRedPower_Enabled"))) {
            plugins.put("RedPowerWorld", new pluginRedPowerWorld());
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginEE_Enabled"))) {
            plugins.put("EE", new pluginEE());
        }
        if (denLib.convertToBoolean(core.config.getOption("UpdateCheck"))) {
            plugins.put("Updater", new pluginUpdater());
        }
    }

    public static void registerEarlyPlugins() {
        if (denLib.convertToBoolean(core.config.getOption("pluginBetterFarming_Enabled"))) {
            plugins.put("BetterFarming", new pluginBetterFarming());
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginMineFactoryReloaded_Enabled"))) {
            plugins.put("MFR", new pluginMineFactoryReloaded());
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginForestry_Enabled"))) {
            plugins.put("Forestry", new pluginForestry());
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginIc2_Enabled"))) {
//            plugins.put("IC2", new pluginIC2());
        }
        if (denLib.convertToBoolean(core.config.getOption("pluginBuildcraft_Enabled"))) {
            plugins.put("Buildcraft", new pluginBuildcraft());
        }
        plugins.put("CoreItems", new pluginCoreItems());
        //plugins.put("BluesFood",new pluginbluesFood());
    }

    public static boolean isPluginAlive(String name) {
        boolean loaded = false;
        if (plugins.get(name) != null) {
            loaded = plugins.get(name).loaded;
        }
        return loaded;
    }
}
