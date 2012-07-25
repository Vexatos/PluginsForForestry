package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.items.*;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;

public class blueswaxModule extends baseModule {

    public static waxCast extrawax;
    public static waxBlock thatch;
    public static waxSlab thatchslab;

    public blueswaxModule(pluginBase parent) {
        super(parent);
    }

    public static void load(pluginBase parent) {
        baseModule b = new blueswaxModule(parent);
        b.register();
    }

    @Override
    protected void defaults() {
        this.parent.config.addDefault("# These options are for Blue's Wax Stuff");
        this.parent.config.addDefault("# Includes Wax Casts, wand of freezing, wax tablets");
        this.parent.config.addDefault("BluesWaxStuff_Enabled=true");
        this.parent.config.addDefault("BluesWaxStuff_ItemID=5317");
        this.parent.config.addDefault("ThatchedRoof_Enabled=true");
        this.parent.config.addDefault("ThatchedRoof_BlockID=181");
        this.parent.config.addDefault("ThatchedRoofSlab_BlockID=185");
    }

    @Override
    protected void init() {
        if (denLib.convertToBoolean(this.parent.config.getOption("BluesWaxStuff_Enabled"))) {
            //------------------------------------------------
            extrawax = new waxCast(Integer.valueOf(this.parent.config.getOption("BluesWaxStuff_ItemID")), "bluesextrawaxitems");
            extrawax.metaMap.put("BLANK SPACE DO NOT USE", 0);
            extrawax.metaMap.put("Wax Tablet", 1);
            extrawax.metaMap.put("Wax Cast", 2);
            extrawax.metaMap.put("Filled Wax Cast", 3);
            extrawax.metaMap.put("Refractory Cast", 4);
            extrawax.metaMap.put("Filled Wax Cast_Red", 5);
            extrawax.metaMap.put("Lava Cast", 6);
            extrawax.metaMap.put("Rod of Freezing", 7);
            extrawax.metaMap.put("Test", 8);
            //-------------------------------------------------
            extrawax.add("waxplaceholder", 0, 15, "PLACEHOLDER");
            extrawax.add("waxtablet", extrawax.metaMap.get("Wax Tablet"), 15, "Wax Tablet");
            extrawax.add("waxcast", extrawax.metaMap.get("Wax Cast"), 15 - 1, "Wax Cast");
            extrawax.add("filledwaxcast", extrawax.metaMap.get("Filled Wax Cast"), 15 - 2, "Filled Wax Cast");
            extrawax.add("waxcast_red", extrawax.metaMap.get("Refractory Cast"), 30, "Refractory Cast");
            extrawax.add("wascastfilled_red", extrawax.metaMap.get("Filled Wax Cast_Red"), 29, "Filled Wax Cast");
            extrawax.add("waxcastlava_red", extrawax.metaMap.get("Lava Cast"), 28, "Lava Cast");
            extrawax.add("rodoffreezing", extrawax.metaMap.get("Rod of Freezing"), 31, "Rod of Freezing", 1);
            extrawax.add("test", extrawax.metaMap.get("Test"), 12, "Test (Ignore Me)");
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.waterStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Filled Wax Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Cast")), false));
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.lavaStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.waterStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Filled Wax Cast_Red")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));
            //-------------------------------------------------
            if (this.getOptionBool("ThatchedRoof_Enabled")) {
                thatch = new waxBlock(this.getOptionInt("ThatchedRoof_BlockID"), Material.clay, "Thatch");
                thatch.add("thatch", "Thatched Double Slab", 0, new Integer[]{8, 8, 9, 9, 9, 9});
                waxBlockItem.names.put(0, "Test 1");
                thatchslab = new waxSlab(this.getOptionInt("ThatchedRoofSlab_BlockID"), Material.clay, "ThatchSlab");
                thatchslab.add("thatchslab", "Thatched Slab", 0, new Integer[]{8, 8, 9, 9, 9, 9});
                waxSlabItem.names.put(0, "Thatched Slab");
            }
            recipes();
        }
    }

    @Override
    protected void recipes() {
        //--------------------------
        // BLUE'S BEES WAX STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("BluesWaxStuff_Enabled"))) {
            ModLoader.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "XSX",
                        "XBX",
                        "XBX",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            ModLoader.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "SXX",
                        "BXX",
                        "BXX",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            ModLoader.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "XXS",
                        "XXB",
                        "XXB",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "WWX",
                        "WWX",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XXX",
                        "WWX",
                        "WWX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XWW",
                        "XWW",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XXX",
                        "XWW",
                        "XWW",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Block.bookShelf, 1, 0), new Object[]{
                        "PPP",
                        "TTT",
                        "PPP",
                        Character.valueOf('P'), Block.planks,
                        Character.valueOf('T'), new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet"))});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Cast")), new Object[]{
                        "WWW",
                        "WXW",
                        "WWW",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), new Object[]{
                        "WWW",
                        "WXW",
                        "WWW",
                        Character.valueOf('W'), ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Rod of Freezing")), new Object[]{
                        "GIG",
                        "ISI",
                        "BsB",
                        Character.valueOf('G'), Block.glass,
                        Character.valueOf('I'), Item.ingotIron,
                        Character.valueOf('S'), Item.snowball,
                        Character.valueOf('B'), new ItemStack(Item.dyePowder, 1, 12),
                        Character.valueOf('s'), Item.stick});
            RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(Block.lavaStill.blockID, 1000), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")));
            if (this.getOptionBool("ThatchedRoof_Enabled")) {
                Item r[] = new Item[]{Item.wheat, Item.reed, ItemInterface.getItem("mulch").getItem()};
                int q = 6;
                for (Item i : r) {
                    ModLoader.addRecipe(new ItemStack(thatchslab, q, 0), new Object[]{
                                "www",
                                "iii",
                                "www",
                                Character.valueOf('w'), ItemInterface.getItem("beeswax"),
                                Character.valueOf('i'), new ItemStack(i)});
                    ModLoader.addRecipe(new ItemStack(thatchslab, q, 0), new Object[]{
                                "www",
                                "iii",
                                "www",
                                Character.valueOf('w'), ItemInterface.getItem("refractoryWax"),
                                Character.valueOf('i'), new ItemStack(i)});
                }
            }
            ModLoader.addShapelessRecipe(new ItemStack(Block.stone), new Object[]{
                        ItemInterface.getItem("beeswax"),
                        new ItemStack(Block.cobblestone)});
            ModLoader.addShapelessRecipe(new ItemStack(Block.waterlily), new Object[]{
                        new ItemStack(Item.wheat),
                        ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.blazeRod), new Object[]{
                        "RGR",
                        "PSP",
                        "RGR",
                        Character.valueOf('R'), ItemInterface.getItem("refractoryWax"),
                        Character.valueOf('G'), new ItemStack(Item.lightStoneDust),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('P'), ItemInterface.getItem("phosphor")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XXF",
                        "XXS",
                        "XXB",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XXF",
                        "XXS",
                        "XXB",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            ItemStack b = ItemInterface.getItem("beeswax");
            ItemStack r2[] = new ItemStack[]{new ItemStack(Block.sand),
                new ItemStack(Block.dirt),
                new ItemStack(Block.gravel)};
            for (ItemStack i : r2) {
                ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[]{
                            b,
                            i,
                            b,
                            i});
            }
            b = ItemInterface.getItem("refractoryWax");
            for (ItemStack i : r2) {
                ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[]{
                            b,
                            i,
                            b,
                            i});
            }
            r2 = new ItemStack[]{new ItemStack(Block.cobblestoneMossy),
                new ItemStack(Block.stoneBrick, 1, 1)};
            ItemStack r3[] = new ItemStack[]{new ItemStack(Block.cobblestone),
                new ItemStack(Block.stoneBrick)};
            for (int i = 0; i < r2.length; i++) {
                ModLoader.addShapelessRecipe(r2[i], new Object[]{
                            ItemInterface.getItem("royalJelly"),
                            r3[i]});
            }
            ModLoader.addShapelessRecipe(new ItemStack(Block.stoneBrick, 1, 2), new Object[]{
                        ItemInterface.getItem("phosphor"),
                        new ItemStack(Block.stoneBrick)});

        }
    }
    //--------------------------
}
