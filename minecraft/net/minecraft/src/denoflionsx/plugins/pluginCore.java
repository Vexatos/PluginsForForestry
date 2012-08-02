package net.minecraft.src.denoflionsx.plugins;

import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.HashMap;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.items.Tools;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.TankManager;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.goldGear;
import net.minecraft.src.denoflionsx.plugins.Forestry.pipette;

public class pluginCore {

    /*
     * This is the core file for plugins. plugins are managed here.
     * registerPlugins is called by the main core.
     */
    public static Tools metaItem;
    public static ItemStack[] containers = new ItemStack[4];
    public static ItemStack[] filled = new ItemStack[4];
    public static final boolean isRelease = false;
    public static final boolean debugmode = false;
    public static final int bottle = (int) Math.floor((1000 / 4));
    public static HashMap<String, pluginBase> plugins = new HashMap();

    public static void registerLatePlugins() {

        try {
            if (denLib.convertToBoolean(core.config.getOption("pluginRedPower_Enabled"))) {
                plugins.put("RedPowerWorld", new pluginRedPowerWorld());
            }
            if (denLib.convertToBoolean(core.config.getOption("pluginEE_Enabled"))) {
                plugins.put("EE", new pluginEE());
            }
            if (denLib.convertToBoolean(core.config.getOption("UpdateCheck"))) {
                plugins.put("Updater", new pluginUpdater());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void registerEarlyPlugins() {
        containers[0] = new ItemStack(Item.bucketEmpty, 1, 0);
        containers[1] = ItemInterface.getItem("waxCapsule");
        containers[2] = ItemInterface.getItem("canEmpty");
        containers[3] = ItemInterface.getItem("refractoryEmpty");
        filled[0] = new ItemStack(Item.bucketMilk, 1, 0);
        metaItem = new Tools(Integer.valueOf(core.config.getOption("liquidVacuum_ID")), "multiItem");
        metaItem.metaMap.put("Milker", 0);
        metaItem.metaMap.put("Milk Capsule", 1);
        metaItem.metaMap.put("Milk Can", 2);
        metaItem.metaMap.put("Milk Capsule_Red", 3);
        metaItem.metaMap.put("Heavy Water Capsule", 4);
        metaItem.metaMap.put("Heavy Water Can", 5);
        metaItem.metaMap.put("Heavy Water Capsule_Red", 6);
        metaItem.metaMap.put("Heavy Water Bottle", 7);
        metaItem.metaMap.put("Heavy Water Cell", 8);
        metaItem.metaMap.put("Milk Bottle", 9);
        metaItem.metaMap.put("Milk Cell", 10);
        metaItem.metaMap.put("Wooden Bucket", 11);
        metaItem.metaMap.put("Filled Wooden Bucket", 12);
        metaItem.metaMap.put("Debug Tool", 13);
        metaItem.metaMap.put("Milk Bag", 14);
        metaItem.add("liquidvacuum", metaItem.metaMap.get("Milker"), Colors.shiftRow(3, 2), "Liquid Vacuum", 1);
        metaItem.add("woodenbucket", metaItem.metaMap.get("Wooden Bucket"), Colors.shiftRow(3,1), "Wooden Bucket", 1);
        metaItem.add("filledwoodenbucket", metaItem.metaMap.get("Filled Wooden Bucket"), Colors.shiftRow(4,1), "Filled Wooden Bucket", 1);
        metaItem.add("milkbag", metaItem.metaMap.get("Milk Bag"), Colors.shiftRow(4,2), "Milk Bag");
        if (debugmode) {
            metaItem.add("debugtool", metaItem.metaMap.get("Debug Tool"), Colors.shiftRow(15, 6), "Debug Tool", 1);
            ModLoader.addRecipe(new ItemStack(metaItem, 1, metaItem.metaMap.get("Debug Tool")), new Object[]{
                        "XXX",
                        "XDX",
                        "XXX",
                        Character.valueOf('D'), Block.dirt});
        }
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(Block.waterStill, 1000), new ItemStack(metaItem, 1, metaItem.metaMap.get("Wooden Bucket")), new ItemStack(metaItem, 1, metaItem.metaMap.get("Filled Wooden Bucket")));
        try {
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
                plugins.put("IC2", new pluginIC2());
            }
            if (denLib.convertToBoolean(core.config.getOption("pluginBuildcraft_Enabled"))) {
                plugins.put("Buildcraft", new pluginBuildcraft());
            }
            if (denLib.convertToBoolean(core.config.getOption("pluginAdvancedPowerSystems_Enabled"))) {
                plugins.put("APS", new pluginAdvancedPowerSystems());
            }
            ItemStack vac = new ItemStack(metaItem, 1, metaItem.metaMap.get("Milker"));
            if (plugins.get("Forestry").loaded) {
                Object recipe[] = new Object[]{
                    "PpP",
                    "SgS",
                    "GBG",
                    Character.valueOf('P'), ItemInterface.getItem("propolis"),
                    Character.valueOf('p'), new ItemStack(pipette.pipette),
                    Character.valueOf('S'), ItemInterface.getItem("sturdyMachine"),
                    Character.valueOf('g'), new ItemStack(Item.glassBottle),
                    Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                    Character.valueOf('B'), new ItemStack(Item.bucketEmpty)};
                RecipeManagers.carpenterManager.addRecipe(100, new LiquidStack(Block.waterStill.blockID, 1000), null, vac, recipe);
            } else if (plugins.get("Buildcraft").loaded && !plugins.get("Forestry").loaded) {
                Object recipe[] = new Object[]{
                    "LgL",
                    "ITI",
                    "GBG",
                    Character.valueOf('L'), Item.leather,
                    Character.valueOf('g'), Item.glassBottle,
                    Character.valueOf('I'), Block.blockSteel,
                    Character.valueOf('T'), TankManager.TankBlock,
                    Character.valueOf('G'), goldGear.goldGear,
                    Character.valueOf('B'), Item.bucketEmpty};
                ModLoader.addRecipe(vac, recipe);
            }
            ModLoader.addRecipe(new ItemStack(metaItem, 1, metaItem.metaMap.get("Wooden Bucket")), new Object[]{
                        "XXX",
                        "PXP",
                        "XPX",
                        Character.valueOf('P'), Block.wood});
            ModLoader.addRecipe(new ItemStack(metaItem, 1, metaItem.metaMap.get("Wooden Bucket")), new Object[]{
                        "PXP",
                        "XPX",
                        "XXX",
                        Character.valueOf('P'), Block.wood});
            for (int i = 0; i < containers.length; i++) {
                ModLoader.addShapelessRecipe(filled[i], new Object[]{
                            new ItemStack(metaItem, 1, metaItem.metaMap.get("Milk Bag")),
                            containers[i]});
            }
        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

    public static boolean isPluginAlive(String name) {
        boolean loaded = false;
        if (plugins.get(name) != null) {
            loaded = plugins.get(name).loaded;
        }
        return loaded;
    }
}
