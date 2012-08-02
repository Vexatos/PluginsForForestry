package net.minecraft.src.denoflionsx.core;

import java.util.ArrayList;
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

    protected static String version = "1.2d";
    public static final boolean client = true;
    public static boolean isBukkit = false;
    public static Config config = new Config("PluginsforForestry.cfg");
    public static int[] ItemIDs;
    public static ArrayList<Integer> ItemIDs_New = new ArrayList();
    public static boolean isBetaBuild = false;
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
    
    public static void depricatedItemID(){
        // Converting to an arraylist system instead of an array.
        // Use this method to bandaid old code for now.
        ItemIDs_New.add(5312); //0
        ItemIDs_New.add(5313); //1
        ItemIDs_New.add(5314); //2
        ItemIDs_New.add(5315); //3
        ItemIDs_New.add(5316); //4
        ItemIDs_New.add(5317); //5
        ItemIDs_New.add(5318); //6
        ItemIDs_New.add(5319); //7
        ItemIDs_New.add(5320); //8
        ItemIDs_New.add(5321); //9 Pumpkin Juice.
        ItemIDs_New.add(5322); //10 Melon Juice.
        ItemIDs_New.add(5323); //11 Sugary Peat Bar.
        ItemIDs = new int[ItemIDs_New.size()];
        int count = 0;
        for (int i : ItemIDs_New){
            ItemIDs[count] = i;
            count++;
        }
    }

    // This function runs first.
    public static void runCoreFunctions() {
        depricatedItemID();
        MinecraftForgeClient.preloadTexture(mod_PluginsforForestry.texture);
        ModLoader.registerTileEntity(TileEntityMachine.class, "dolxMachine");
        ModLoader.registerTileEntity(TileUniversalSapling.class,"dolxUSapling");
        
        defaults.setup();
        config.readFile();
        Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
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
