package denoflionsx.plugins.Forestry.Modules.BlueWaxModule;

import denoflionsx.Enums.Placeholder;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.Annotations.doesSpecialEvent;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.EnumDyeColors;
import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.core.PfFModuleTemplate;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer.LiquidManagerWrapper;
import net.minecraftforge.liquids.LiquidStack;

public class BlueWaxmodule extends PfFModuleTemplate {

    private ItemIDManager wax = new ItemIDManager(1, "ExtraWax");
    private ItemIDManager rod = new ItemIDManager(1, "RodofFreezing");
    private ItemIDManager fuel = new ItemIDManager(1, "waxcastfuels");
    public static waxCast extrawax;
    public static ItemRodofFreezing Rod;
    public static WaxCastFuels fuels;
    private int WaxCastPerCraft;
    private int arrowsPerCraft;
    private int tabletsPerCraft;

    public BlueWaxmodule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("# These options are for Blue's Wax Stuff");
        this.config.addDefault("# Includes Wax Casts, wand of freezing, wax tablets");
        this.config.addDefault("BluesWaxStuff_Enabled=true");
        this.config.addDefault("BluesWaxStuff_ItemID=" + wax.getItemIDs().get(0));
        this.config.addDefault("RodOfFreezing_ItemID=" + rod.getItemIDs().get(0));
        this.config.addDefault("WaxCastFuel_Enabled=" + "true");
        this.config.addDefault("WaxCastFuel_ItemID=" + fuel.getItemIDs().get(0));
        this.config.addDefault("WaxCast_AmountPerCraft=" + 4);
        this.config.addDefault("Arrows_AmountPerCraft=" + 3);
        this.config.addDefault("WaxTablet_AmountPerCraft=" + 3);
    }

    @Override
    @doesSpecialEvent
    public void doSetup() {
        //------------------------------------------------
        extrawax = new waxCast(Integer.valueOf(this.config.getOption("BluesWaxStuff_ItemID")), "bluesextrawaxitems");
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
        extrawax.add("waxcast_red", extrawax.metaMap.get("Refractory Cast"), EnumCastTextures.REFRACTORY.getIndex(), "Refractory Cast");
        extrawax.add("wascastfilled_red", extrawax.metaMap.get("Filled Wax Cast_Red"), EnumCastTextures.WATER.getIndex(), "Filled Wax Cast");
        extrawax.add("waxcastlava_red", extrawax.metaMap.get("Lava Cast"), EnumCastTextures.LAVA.getIndex(), "Lava Cast");
        LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.lavaStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));
        LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.waterStill, 1), new ItemStack(extrawax, 1, extrawax.metaMap.get("Filled Wax Cast_Red")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), false));

        Rod = new ItemRodofFreezing(this.config.getOptionInt("RodOfFreezing_ItemID"));
        if (this.config.getOptionBool("WaxCastFuel_Enabled")) {
            fuels = new WaxCastFuels(this.config.getOptionInt("WaxCastFuel_ItemID"), "WaxCastFuel");
            PfFEvents.specialEvent.notifyListeners(EnumEventSpecialMessages.CAST.getMsg());
        }
    }

    @Override
    public void recipes() {
        //--------------------------
        // BLUE'S BEES WAX STUFF
        //--------------------------
        this.WaxCastPerCraft = this.config.getOptionInt("WaxCast_AmountPerCraft");
        this.arrowsPerCraft = this.config.getOptionInt("Arrows_AmountPerCraft");
        this.tabletsPerCraft = this.config.getOptionInt("WaxTablet_AmountPerCraft");
        if (denLib.convertToBoolean(this.config.getOption("BluesWaxStuff_Enabled"))) {
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
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.tabletsPerCraft, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "WWX",
                        "WWX",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.tabletsPerCraft, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XXX",
                        "WWX",
                        "WWX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.tabletsPerCraft, extrawax.metaMap.get("Wax Tablet")), new Object[]{
                        "XWW",
                        "XWW",
                        "XXX",
                        Character.valueOf('W'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.tabletsPerCraft, extrawax.metaMap.get("Wax Tablet")), new Object[]{
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
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(extrawax, this.WaxCastPerCraft, extrawax.metaMap.get("Refractory Cast")), new Object[]{
                        "WWW",
                        "WXW",
                        "WWW",
                        Character.valueOf('W'), ItemInterface.getItem("refractoryWax")});
            RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(Block.lavaStill.blockID, 1000), new ItemStack(extrawax, 1, extrawax.metaMap.get("Refractory Cast")), new ItemStack(extrawax, 1, extrawax.metaMap.get("Lava Cast")));
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
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
                        "XXF",
                        "XXS",
                        "XXB",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
                        "FXX",
                        "SXX",
                        "BXX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
                        "XFX",
                        "XSX",
                        "XBX",
                        Character.valueOf('F'), new ItemStack(Item.flint),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('B'), ItemInterface.getItem("refractoryWax")});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(Item.arrow, this.arrowsPerCraft, 0), new Object[]{
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
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.ice), new Object[]{PfFManagers.ItemManager.getItem("wascastfilled_red"), PfFManagers.ItemManager.getItem("rodoffreezing")});
        FMLWrapper.MODE.FML.addShapelessRecipe(new ItemStack(Block.obsidian), new Object[]{PfFManagers.ItemManager.getItem("waxcastlava_red"), PfFManagers.ItemManager.getItem("rodoffreezing")});
    }
}
