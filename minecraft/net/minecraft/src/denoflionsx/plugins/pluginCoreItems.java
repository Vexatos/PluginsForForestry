package net.minecraft.src.denoflionsx.plugins;

import forestry.api.core.ItemInterface;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.Core.EnumToolTextures;
import net.minecraft.src.denoflionsx.plugins.Core.LiquidVacuum;
import net.minecraft.src.denoflionsx.plugins.Core.MilkBag;
import net.minecraft.src.denoflionsx.plugins.Core.WoodenBucket;
import net.minecraft.src.denoflionsx.plugins.Forestry.SqueezerWrapper;
import net.minecraft.src.denoflionsx.plugins.Forestry.pipette;

public class pluginCoreItems extends pluginBase {

    ItemIDManager IDs = new ItemIDManager(2, "LiquidVacuum");
    ItemIDManager woodenbucketIDs = new ItemIDManager(1, "WoodenBucket");
    LiquidVacuum lv;
    MilkBag mb;
    WoodenBucket wb;

    public pluginCoreItems() {
        this.name = "pluginCoreItems";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!this.loaded) {
            this.defaults();
            this.runConfig();
            if (this.loaded = this.init()) {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    @Override
    protected void recipes() {
        SqueezerWrapper.add(API.getItem("milkbag"), new ItemStack(Item.leather), 10, API.getItem("milk"), 1000);
        ModLoader.addRecipe(API.getItem("liquidvacuum"), new Object[]{
                    "PpP",
                    "MbM",
                    "GBG",
                    Character.valueOf('P'), ItemInterface.getItem("propolis"),
                    Character.valueOf('p'), new ItemStack(pipette.pipette),
                    Character.valueOf('M'), ItemInterface.getItem("sturdyMachine"),
                    Character.valueOf('b'), new ItemStack(Item.glassBottle),
                    Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                    Character.valueOf('B'), new ItemStack(Item.bucketEmpty)});
        ModLoader.addRecipe(API.getItem("woodenbucket"), new Object[]{
                    "WXW",
                    "XWX",
                    "XXX",
                    Character.valueOf('W'), new ItemStack(Block.wood)});
    }

    @Override
    protected boolean init() {
        this.hooked = true;
        if (this.config.getOptionBool("LiquidVacuum_Enabled")) {
            lv = new LiquidVacuum(this.config.getOptionInt("LiquidVacuum_ItemID"), "liquidvacuum");
            lv.add("liquidvacuum", 0, EnumToolTextures.ToolTextures.LIQUIDVACUUM.getIndex(), "Liquid Vacuum");
            mb = new MilkBag(this.config.getOptionInt("MilkBag_ItemID"), "milkbag");
            mb.add("milkbag", 0, EnumToolTextures.ToolTextures.MILKBAG.getIndex(), "Milk Bag");
        }
        if (this.config.getOptionBool("WoodenBucket_Enabled")) {
            wb = new WoodenBucket(this.config.getOptionInt("WoodenBucket_ItemID"), "woodenbucket");
            wb.add("woodenbucket", wb.metaMap.get("Wooden Bucket"), EnumToolTextures.ToolTextures.WOODENBUCKET_EMPTY.getIndex(), "Wooden Bucket");
            wb.add("filledwoodenbucket", wb.metaMap.get("Filled Wooden Bucket"), EnumToolTextures.ToolTextures.WOODENBUCKET_FULL.getIndex(), "Filled Wooden Bucket");
            WoodenBucket.bucketWorksInNether = this.config.getOptionBool("bucketWorksInNether");
        }
        return this.hooked;
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[Core Items Config]");
        this.config.addDefault("LiquidVacuum_Enabled=" + "true");
        this.config.addDefault("LiquidVacuum_ItemID=" + IDs.getItemIDs().get(0));
        this.config.addDefault("MilkBag_ItemID=" + IDs.getItemIDs().get(1));
        this.config.addDefault("WoodenBucket_Enabled=" + "true");
        this.config.addDefault("WoodenBucket_ItemID=" + woodenbucketIDs.getItemIDs().get(0));
        this.config.addDefault("bucketWorksInNether=" + "false");
    }
}
