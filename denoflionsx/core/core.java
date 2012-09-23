package denoflionsx.core;

import denoflionsx.API.Objects.PfFLiquid;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.denLib.denLib;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.Forestry.Modules.BlueWaxModule.BlueWaxmodule;
import denoflionsx.plugins.PluginRegistry;
import denoflionsx.plugins.pluginCoreItems;

/*
 * Main core file. This file passes everything we need to load on to the mod
 * file.
 */

// THIS AND PLUGINREGISTRY ARE GETTING AN OVERHAUL. 
// STOP ADDING NEW SHIT IN HERE.

public class core{

    protected static String version = "1.3Dev";
    public static boolean isBukkit = false;
    public static boolean isBetaBuild = true;
    public static CoreObject PfFCore = new CoreObject();
    
    public static boolean isClient() {
        return PluginsforForestry.proxy.isClient();
    }

    // This is for plugins that need to load after
    // everything else at all costs.
    public static void registerLatePlugins() {
        EnumForestryLiquids.values();
        PluginRegistry.registerLatePlugins();
    }

    // This is for plugins that can load whenever.
    public static void registerEarlyPlugins() {
        PluginRegistry.registerEarlyPlugins();
        registerFX();
        PfFManagers.FermenterManager.createRecipes();
    }

    public static void registerSpecial() {
        for (PfFLiquid l : PfFManagers.ContainerManager.getLiquids()) {
            if (BlueWaxmodule.fuels != null) {
                BlueWaxmodule.fuels.addLiquid(l);
            }
            if (pluginCoreItems.fuels != null){
                pluginCoreItems.fuels.addLiquid(l);
            }
            if (pluginCoreItems.bfuels != null){
                pluginCoreItems.bfuels.addLiquid(l);
            }
        }
    }

    public static void registerFX() {
        PluginsforForestry.proxy.registerFX();
    }

    public static String modVersion() {
        return version;
    }

    public static void print(String msg) {

        denLib.print("[PluginsForForestry]: " + msg);

    }

    public static String addName(String name) {
        if (isClient()) {
            return denLib.addName(name);
        } else {
            return "";
        }
    }

    public static String BukkitShift(String m) {
        return "net.minecraft.server." + m;
    }
}
