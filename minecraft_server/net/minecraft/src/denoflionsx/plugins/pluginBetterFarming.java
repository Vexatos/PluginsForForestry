package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.BetterFarming.*;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;

public class pluginBetterFarming extends pluginBase {

    public static multiItem citrusJuice;
    protected String Class = "";

    public pluginBetterFarming() {
        mod = "mod_BetterFarming";
        name = "pluginBetterFarming";
        Class = mod;
        config = new Config("pluginBetterFarming.cfg");
        if (core.isBukkit) {
            Class = "net.minecraft.server." + Class;
        }
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            core.print(mod + " not found!");
            return hooked;
        }

        String BCT = "BuildCraftTransport";
        if (core.isBukkit) {
            BCT = "net.minecraft.server." + BCT;
        }
        this.addItem(Class, "appleSeeds", "Apple Seeds", 0);
        this.addItem(Class, "orangeSeeds", "Orange Seeds", 0);
        this.addItem(Class, "cocoaSeeds", "Cocoa Seeds", 0);
        this.addItem(Class, "mintSeeds", "Mint Seeds", 0);
        this.addItem(Class, "lemonSeeds", "Lemon Seeds", 0);
        this.addItem(Class, "orange", "Orange", 0);
        this.addItem(Class, "lemon", "Lemon", 0);
        this.addItem(Class, "mint", "Mint", 0);
        this.addItem(Class, "milkBottle", "Milk Bottle", 0);
        this.addBlock(Class, "cocoaTree", "Cocoa Tree", 0);
        this.addBlock(Class, "appleTree", "Apple Tree", 0);
        this.addBlock(Class, "orangeTree", "Orange Tree", 0);
        this.addBlock(Class, "lemonTree", "Lemon Tree", 0);
        this.addBlock(Class, "mintCrop", "Mint Crop", 0);
        this.addItem(BCT, "pipeWaterproof", "Waterproofing", 0);

        if (denLib.convertToBoolean(config.getOption("CitrusJuice_Enabled"))) {
            citrusJuice = new multiItem(Integer.valueOf(config.getOption("CitrusJuice_ItemID")), "citrusjuice");
            citrusJuice.metaMap.put("Citrus Juice", 0);
            citrusJuice.metaMap.put("Citrus Capsule", 1);
            citrusJuice.metaMap.put("Citrus Can", 2);
            citrusJuice.metaMap.put("Citrus Capsule_Red", 3);
            citrusJuice.metaMap.put("Citrus Bucket", 4);
            citrusJuice.metaMap.put("Citrus Bottle", 5);
            citrusJuice.add("citrusjuice", citrusJuice.metaMap.get("Citrus Juice"), 3, "Citrus Juice");
            citrusJuice.add("citruscap", citrusJuice.metaMap.get("Citrus Capsule"), 35, "Citrus Capsule");
            citrusJuice.add("citruscan", citrusJuice.metaMap.get("Citrus Can"), 19, "Citrus Can");
            citrusJuice.add("citruscap_red", citrusJuice.metaMap.get("Citrus Capsule_Red"), 51, "Citrus Capsule");
            citrusJuice.add("citrusbucket", citrusJuice.metaMap.get("Citrus Bucket"), 35 - 1, "Citrus Bucket", 1);
            citrusJuice.add("citrusbottle", citrusJuice.metaMap.get("Citrus Bottle"), 5 + (16 * 3), "Citrus Bottle");
        }
        hooked = true;
        if (hooked) {
            core.print(getName() + " Loaded!");
        }
        return hooked;
    }

    @Override
    protected void recipes() {

        try {
            int s = Integer.valueOf(config.getOption("Seeds_SeedOilPerSqueeze"));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Apple Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Orange Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Cocoa Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Mint Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, s));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Lemon Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, s));
            if (denLib.convertToBoolean(config.getOption("CitrusJuice_Enabled"))) {
                int s2 = Integer.valueOf(config.getOption("CitrusJuice_AmountPerSqueeze"));
                int s3 = Integer.valueOf(config.getOption("CitrusJuice_PercentChanceOfMulch"));
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(this.items.get("Lemon").getItem())}, new LiquidStack(new ItemStack(citrusJuice, 1, 0).itemID, s2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), s3);
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(this.items.get("Orange").getItem())}, new LiquidStack(new ItemStack(citrusJuice, 1, 0).itemID, s2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), s3);
                addFermenterRecipes.add(new ItemStack(citrusJuice), 1.5F);
                FuelManager.bronzeEngineFuel.put(citrusJuice.shiftedIndex, new EngineBronzeFuel(new ItemStack(citrusJuice, 1, citrusJuice.metaMap.get("Citrus Juice")), this.getOptionInt("CitrusJuice_MJt"), this.getOptionInt("CitrusJuice_BurnTime"), 1));
            }
            if (denLib.convertToBoolean(config.getOption("ForestryIntegration"))) {
                growHook.engage();
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Cocoa Tree"), new ItemStack(Item.dyePowder, 1, 3), new int[]{this.items.get("Cocoa Seeds").itemID, new ItemStack(Item.dyePowder, 1, 3).itemID}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Apple Tree"), new ItemStack(Item.appleRed, 1, 0), new int[]{this.items.get("Apple Seeds").itemID, Item.appleRed.shiftedIndex}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Orange Tree"), new ItemStack(this.items.get("Orange").getItem(), 1, 0), new int[]{this.items.get("Orange Seeds").itemID, this.items.get("Orange").itemID}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Lemon Tree"), new ItemStack(this.items.get("Lemon").getItem(), 1, 0), new int[]{this.items.get("Lemon Seeds").itemID, this.items.get("Lemon").itemID}));
                CropProviders.cerealCrops.add(new cropCustomSeedProvider(this.items.get("Mint").getItem(), this.items.get("Mint Seeds").getItem(), this.getBlock("Mint Crop"), 5));
            }
            ModLoader.addRecipe(this.items.get("Waterproofing"), new Object[]{
                        "XXX",
                        "XMX",
                        "XXX",
                        Character.valueOf('M'), this.items.get("Mint")});
            addFermenterRecipes.addItem(this.items.get("Mint").getItem(), 150, this);
            LiquidContainerSystem.create(citrusJuice);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void register() {
        if (!loaded) {
            defaults();
            if (loaded = init()) {
                recipes();
            }
        }
    }

    @Override
    protected void defaults() {
        config.addDefault("[BetterFarming Options]");
        config.addDefault("Seeds_SeedOilPerSqueeze=100");
        config.addDefault("# This option makes BetterFarming plants work in the Forestry Arboretum and Logger");
        config.addDefault("ForestryIntegration=true");
        config.addDefault("# These options are for Citrus Juice");
        config.addDefault("# Citrus Juice is made with BetterFarming fruit in a squeezer");
        config.addDefault("CitrusJuice_Enabled=true");
        config.addDefault("CitrusJuice_ItemID=5313");
        config.addDefault("CitrusJuice_AmountPerSqueeze=200");
        config.addDefault("CitrusJuice_PercentChanceOfMulch=40");
        config.addDefault("CitrusJuice_MJt=1");
        config.addDefault("CitrusJuice_BurnTime=10000");
        config.writeConfig();
        config.readFile();

    }
}
