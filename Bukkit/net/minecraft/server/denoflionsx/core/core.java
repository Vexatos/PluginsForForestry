package net.minecraft.server.denoflionsx.core;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.pluginCore;
import net.minecraft.server.denoflionsx.plugins.Forestry.Trees.TileUniversalSapling;
import net.minecraft.server.denoflionsx.plugins.IC2.TileEntityMachine;
import net.minecraft.server.ModLoader;

public class core
{
    protected static String version = "1.2d";
    public static final boolean client = false;
    public static boolean isBukkit = true;
    public static Config config = new Config("PluginsforForestry.cfg");
    public static int[] ItemIDs = new int[] {5312, 5313, 5314, 5315, 5316, 5317, 5318, 5319, 5320};
    public static boolean isBetaBuild = false;
    public static final int delay = 25;

    public static boolean isClient()
    {
        return false;
    }

    public static void registerLatePlugins()
    {
        pluginCore.registerLatePlugins();
    }

    public static void registerEarlyPlugins()
    {
        pluginCore.registerEarlyPlugins();
    }

    public static void runCoreFunctions()
    {
        ModLoader.registerTileEntity(TileEntityMachine.class, "dolxMachine");
        ModLoader.registerTileEntity(TileUniversalSapling.class, "dolxUSapling");
        defaults.setup();
        config.readFile();
        Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
    }

    public static String modVersion()
    {
        return version;
    }

    public static void print(String var0)
    {
        denLib.print("[PluginsForForestry]: " + var0);
    }

    public static String BukkitShift(String var0)
    {
        return "net.minecraft.server." + var0;
    }
}
