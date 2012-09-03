package net.minecraft.src.denoflionsx.core;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.common.registry.FMLRegistry;
import java.util.ArrayList;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.Achievements.PfFAchievement;
import net.minecraft.src.denoflionsx.MachineTemplate.baseTileEntity;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.items.Containers.Containers;
import net.minecraft.src.denoflionsx.items.Containers.InfusionBar;
import net.minecraft.src.denoflionsx.items.CraftingTools.ItemBlacksmithHammer;
import net.minecraft.src.denoflionsx.items.CraftingTools.ItemIronRing;
import net.minecraft.src.denoflionsx.plugins.BluesFood.MachineOven;
import net.minecraft.src.denoflionsx.plugins.pluginCore;

/*
 * Main core file. This file passes everything we need to load on to the mod
 * file.
 */
public class core {

    protected static String version = "1.2d";
    public static final boolean client = true;
    public static boolean isBukkit = false;
    public static Config config = new Config("PluginsforForestry.cfg");
    public static boolean isBetaBuild = true;
    public static final int delay = 25;
    public static ArrayList<FMLTextureFX> fx = new ArrayList();

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
        registerItemsEnum();
        pluginCore.registerEarlyPlugins();
        registerFX();
        PfFAchievement.registerAchievements();
    }

    public static int addFuel(int id, int meta) {
        return PfFFuelManager.getValue(id);
    }

    public static void registerFX() {
        for (FMLTextureFX f : fx) {
            ModLoader.getMinecraftInstance().renderEngine.registerTextureFX(f);
        }
    }

    // This function runs first.
    public static void runCoreFunctions() {
        PreloadTextureManager.Preload();
        registerTileEntites();
        defaults.setup();
        config.readFile();
        Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
    }

    public static void registerTileEntites() {
        FMLRegistry.registerTileEntity(baseTileEntity.class, "dolBaseEntity");
        FMLRegistry.registerTileEntity(MachineOven.TileEntityOven.class, "dolBlueOven");
    }

    public static void registerItemsEnum() {
        Containers.Container.register();
        ItemBlacksmithHammer.BlacksmithHammer();
        ItemIronRing.IronRing();
        InfusionBar.recipe();
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
