package net.minecraft.src.denoflionsx.core;

import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.Forestry.Trees.TileUniversalSapling;
import net.minecraft.src.denoflionsx.plugins.IC2.TileEntityMachine;
import net.minecraft.src.denoflionsx.plugins.pluginCore;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.mod_PluginsforForestry;

/*
 * Main core file. This file passes everything we need to load on to the mod
 * file.
 */
public class core {

    protected static String version = "1.2 Beta 4";
    public static final boolean client = true;
    public static boolean isBukkit = false;
    public static Config config = new Config("PluginsforForestry.cfg");
    public static int[] ItemIDs = new int[]{5312, 5313, 5314, 5315, 5316, 5317, 5318, 5319, 5320};
    public static boolean isBetaBuild = true;
    public static final int delay = 25;

    public static boolean isClient() {
        return client;
    }

    // This is for plugins that need to load after
    // everything else at all costs.
    public static void registerLatePlugins() {
        pluginCore.registerLatePlugins();
    }

    // This is for plugins that can load whenever.
    public static void registerEarlyPlugins() {

        pluginCore.registerEarlyPlugins();

    }

    // This function runs first.
    public static void runCoreFunctions() {
        MinecraftForgeClient.preloadTexture(mod_PluginsforForestry.texture);
        ModLoader.registerTileEntity(TileEntityMachine.class, "dolxMachine");
        ModLoader.registerTileEntity(TileUniversalSapling.class,"dolxUSapling");
        
        defaults.setup();
        config.readFile();
    }

    public static String modVersion() {
        return version;
    }

    public static void print(String msg) {

        denLib.print("[PluginsForForestry]: " + msg);

    }
    
    public static String BukkitShift(String m){
        return "net.minecraft.server." + m;
    }
}
