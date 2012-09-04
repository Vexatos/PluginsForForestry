package denoflionsx.core;

import cpw.mods.fml.client.FMLTextureFX;
import java.util.ArrayList;
import net.minecraft.src.*;
import denoflionsx.Achievements.PfFAchievement;
import denoflionsx.MachineTemplate.baseTileEntity;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.items.Containers.Containers;
import denoflionsx.items.Containers.InfusionBar;
import denoflionsx.items.CraftingTools.ItemBlacksmithHammer;
import denoflionsx.items.CraftingTools.ItemIronRing;
import denoflionsx.plugins.BluesFood.MachineOven;
import denoflionsx.plugins.pluginCore;

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
        TextureManager.Preload();
        registerTileEntites();
        defaults.setup();
        config.readFile();
        Config.verbose = denLib.convertToBoolean(config.getOption("Verbose"));
    }

    public static void registerTileEntites() {
        FMLWrapper.MODE.FML.registerTileEntity(baseTileEntity.class, "dolBaseEntity");
        FMLWrapper.MODE.FML.registerTileEntity(MachineOven.TileEntityOven.class, "dolBlueOven");
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
