package denoflionsx.core;

import denoflionsx.denLib.denLib;
import denoflionsx.PluginsforForestry;

/*
 * Main core file. This file passes everything we need to load on to the mod
 * file.
 */
// THIS AND PLUGINREGISTRY ARE GETTING AN OVERHAUL. 
// STOP ADDING NEW SHIT IN HERE.
public class core {

    public static boolean isBukkit = false;
    public static boolean isBetaBuild = false;
    public static CoreObject PfFCore = new CoreObject();

    public static boolean isClient() {
        return PluginsforForestry.proxy.isClient();
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
