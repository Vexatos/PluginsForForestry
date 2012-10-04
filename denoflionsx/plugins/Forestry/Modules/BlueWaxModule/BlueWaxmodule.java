package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.Enums.Placeholder;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.Old.baseModule;
import denoflionsx.Old.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.EnumDyeColors;
import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer.LiquidManagerWrapper;

public class BlueWaxmodule extends baseModule {

    private ItemIDManager wax = new ItemIDManager(1, "ExtraWax");
    private ItemIDManager rod = new ItemIDManager(1, "RodofFreezing");
    private ItemIDManager fuel = new ItemIDManager(1, "waxcastfuels");
    public static waxCast extrawax;
    public static ItemRodofFreezing Rod;
    public static WaxCastFuels fuels;
//    public static waxBlock thatch;
//    public static waxSlab thatchslab;
    private int WaxCastPerCraft;

    public BlueWaxmodule(pluginBase parent) {
        super(parent);
    }

    public static void load(pluginBase parent) {
        baseModule b = new BlueWaxmodule(parent);
        b.register();
    }

    @Override
    protected void defaults() {
        this.parent.config.addDefault("# These options are for Blue's Wax Stuff");
        this.parent.config.addDefault("# Includes Wax Casts, wand of freezing, wax tablets");
        this.parent.config.addDefault("BluesWaxStuff_Enabled=true");
        this.parent.config.addDefault("BluesWaxStuff_ItemID=" + wax.getItemIDs().get(0));
        this.parent.config.addDefault("RodOfFreezing_ItemID=" + rod.getItemIDs().get(0));
        this.parent.config.addDefault("WaxCastFuel_Enabled=" + "true");
        this.parent.config.addDefault("WaxCastFuel_ItemID=" + fuel.getItemIDs().get(0));
        this.addDefault("WaxCast_AmountPerCraft=" + 4);
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
            extrawax.add("waxplaceholder", 0, Placeholder.Sprite.RADICAL_EDWARD.getIndex(), "PLACEHOLDER");
            extrawax.add("waxtablet", extrawax.metaMap.get("Wax Tablet"), EnumToolTextures.ToolTextures.WAXTABLET.getIndex(), "Wax Tablet");
            //extrawax.add("waxcast", extrawax.metaMap.get("Wax Cast"), Colors.shiftRow(toShift, 0), "Wax Cast");
            //extrawax.add("filledwaxcast", extrawax.metaMap.get("Filled Wax Cast"), Colors.shiftRow(toShift, 1), "Filled Wax Cast");
            extrawax.add("waxcast_red", extrawax.metaMap.get("Refractory Cast"), EnumCastTextures.REFRACTORY.getIndex(), "Refractory Cast");
            extrawax.add("wascastfilled_red", extrawax.metaMap.get("Filled Wax Cast_Red"), EnumCastTextures.WATER.getIndex(), "Filled Wax Cast");
            extrawax.add("waxcastlava_red", extrawax.metaMap.get("Lava Cast"), EnumCastTextures.LAVA.getIndex(), "Lava Cast");
            //extrawax.add("rodoffreezing", extrawax.metaMap.get("Rod of Freezing"), Colors.shiftRow(toShift, 5), "Rod of Freezing");
            //extrawax.add("test", extrawax.metaMap.get("Test"), 12, "Test (Ignore Me)");
            //LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.waterStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Filled Wax Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Cast")), false));
            LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.lavaStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));
            LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.waterStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Filled Wax Cast_Red")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));
            //-------------------------------------------------
//            if (this.getOptionBool("ThatchedRoof_Enabled")) {
//                thatch = new waxBlock(this.getOptionInt("ThatchedRoof_BlockID"), Material.clay, "Thatch");
//                int faces1[] = new int[]{Colors.shiftRow(3, 7), Colors.shiftRow(4, 7)};
//                thatch.add("thatch", "Thatched Double Slab", 0, new Integer[]{faces1[0], faces1[0], faces1[1], faces1[1], faces1[1], faces1[1]});
//                waxBlockItem.names.put(0, "Test 1");
//                thatchslab = new waxSlab(this.getOptionInt("ThatchedRoofSlab_BlockID"), Material.clay, "ThatchSlab");
//                thatchslab.add("thatchslab", "Thatched Slab", 0, new Integer[]{faces1[0], faces1[0], faces1[1], faces1[1], faces1[1], faces1[1]});
//                waxSlabItem.names.put(0, "Thatched Slab");
//            }
            Rod = new ItemRodofFreezing(this.parent.config.getOptionInt("RodOfFreezing_ItemID"));
            if (this.getOptionBool("WaxCastFuel_Enabled")) {
                fuels = new WaxCastFuels(this.getOptionInt("WaxCastFuel_ItemID"), "WaxCastFuel");
            }
            recipes();
        }
    }

    @Override
    protected void recipes() {
        //--------------------------
        // BLUE'S BEES WAX STUFF
        //--------------------------
        this.WaxCastPerCraft = this.getOptionInt("WaxCast_AmountPerCraft");
        if (denLib.convertToBoolean(this.parent.config.getOption("BluesWaxStuff_Enabled"))) {
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "XSX",
                        "XBX",
                        "XBX",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "SXX",
                        "BXX",
                        "BXX",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Block.torchWood, 2, 0), new Object[]{
                        "XXS",
                        "XXB",
                        "XXB",
                        Character.valueOf('S'), Item.silk,
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")
                    });
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "WWX",
                        "WWX",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XXX",
                        "WWX",
                        "WWX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XWW",
                        "XWW",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XXX",
                        "XWW",
                        "XWW",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Block.bookShelf, 1, 0), new Object[]{
                        "PPP",
                        "TTT",
                        "PPP",
                        Character.valueOf('P'), Block.planks,
                        Character.valueOf('T'), new ItemStack(extrawax, 1, extrawax.metaMap.get("Wax Tablet"))});
//            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.WaxCastPerCraft, extrawax.metaMap.get("Wax Cast")), new Object[]{
//                        "WWW",
//                        "WXW",
//                        "WWW",
//                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.WaxCastPerCraft, extrawax.metaMap.get("Refractory Cast")), new Object[]{
                        "WWW",
                        "WXW",
                        "WWW",
                        Character.valueOf('W'), ItemInterface.getItem("refractoryWax")});
            RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(Block.lavaStill.blockID, 1000), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")));
//            if (this.getOptionBool("ThatchedRoof_Enabled")) {
//                Item r[] = new Item[]{Item.wheat, Item.reed, ItemInterface.getItem("mulch").getItem()};
//                int q = 6;
//                for (Item i : r) {
//                    Crafting.MODE.FML.addRecipe(new ItemStack(thatchslab, q, 0), new Object[]{
//                                "www",
//                                "iii",
//                                "www",
//                                Character.valueOf('w'), ItemInterface.getItem("beeswax"),
//                                Character.valueOf('i'), new ItemStack(i)});
//                    Crafting.MODE.FML.addRecipe(new ItemStack(thatchslab, q, 0), new Object[]{
//                                "www",
//                                "iii",
//                                "www",
//                                Character.valueOf('w'), ItemInterface.getItem("refractoryWax"),
//                                Character.valueOf('i'), new ItemStack(i)});
//                }
//            }
            FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.stone), new Object[]{
                        ItemInterface.getItem("beeswax"),
                        new ItemStack(Block.cobblestone)});
            FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.waterlily), new Object[]{
                        new ItemStack(Item.wheat),
                        ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.blazeRod), new Object[]{
                        "RGR",
                        "PSP",
                        "RGR",
                        Character.valueOf('R'), ItemInterface.getItem("refractoryWax"),
                        Character.valueOf('G'), new ItemStack(Item.lightStoneDust),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('P'), ItemInterface.getItem("phosphor")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XXF",
                        "XXS",
                        "XXB",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow), new Object[]{
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
                FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[]{
                            b,
                            i,
                            b,
                            i});
            }
            b = ItemInterface.getItem("refractoryWax");
            for (ItemStack i : r2) {
                FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[]{
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
                FMLWrapper.MODE.FML.addShapelessRecipe(r2[i], new Object[]{
                            ItemInterface.getItem("royalJelly"),
                            r3[i]});
            }
            FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.stoneBrick, 1, 2), new Object[]{
                        ItemInterface.getItem("phosphor"),
                        new ItemStack(Block.stoneBrick)});

            newRecipes();
        }
    }
    //--------------------------

    private void newRecipes() {
        FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getNewItemStack("waxcast_red", WaxCastPerCraft), new Object[]{
                    "WWW",
                    "WDW",
                    "WWW",
                    Character.valueOf('W'), ItemInterface.getItem("beeswax"),
                    Character.valueOf('D'), EnumDyeColors.DYES.RED.getDye()});
        FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem(denLib.toLowerCaseNoSpaces("Rod of Freezing")), new Object[]{
                    "LSS",
                    "lGS",
                    "IlL",
                    Character.valueOf('L'), EnumDyeColors.DYES.BLUE.getDye(),
                    Character.valueOf('S'), new ItemStack(Item.snowball),
                    Character.valueOf('l'), EnumDyeColors.DYES.LIGHTBLUE.getDye(),
                    Character.valueOf('G'), new ItemStack(Block.blockGold),
                    Character.valueOf('I'), new ItemStack(Item.ingotIron)});
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.ice), new Object[]{PfFManagers.ItemManager.getItem("wascastfilled_red"),PfFManagers.ItemManager.getItem("rodoffreezing")});
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.obsidian), new Object[]{PfFManagers.ItemManager.getItem("waxcastlava_red"),PfFManagers.ItemManager.getItem("rodoffreezing")});
    }
}
