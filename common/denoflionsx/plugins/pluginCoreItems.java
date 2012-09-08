package denoflionsx.plugins;

import forestry.api.core.ItemInterface;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.API.API;
import denoflionsx.core.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Core.*;
import denoflionsx.plugins.Forestry.SqueezerWrapper;
import denoflionsx.plugins.Forestry.pipette;

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
        SqueezerWrapper.add(API.getItem("milkbag"), new ItemStack(Item.leather), 10, API.getItem("milk"), this.config.getOptionInt("MilkBag_AmountPerSqueeze"));
        FMLWrapper.MODE.FML.addRecipe(API.getItem("liquidvacuum"), new Object[]{
                    "PpP",
                    "MbM",
                    "GBG",
                    Character.valueOf('P'), ItemInterface.getItem("propolis"),
                    Character.valueOf('p'), new ItemStack(pipette.pipette),
                    Character.valueOf('M'), ItemInterface.getItem("sturdyCasing"),
                    Character.valueOf('b'), new ItemStack(Item.glassBottle),
                    Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                    Character.valueOf('B'), new ItemStack(Item.bucketEmpty)});
        FMLWrapper.MODE.FML.addRecipe(API.getItem("woodenbucket"), new Object[]{
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
            if (core.isBetaBuild){
                ItemDebugStick.Debug.create();
            }
        }
        return this.hooked;
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[Core Items Config]");
        this.config.addDefault("LiquidVacuum_Enabled=" + "true");
        this.config.addDefault("LiquidVacuum_ItemID=" + IDs.getItemIDs().get(0));
        this.config.addDefault("MilkBag_ItemID=" + IDs.getItemIDs().get(1));
        this.config.addDefault("MilkBag_AmountPerSqueeze=" + 1000);
        this.config.addDefault("WoodenBucket_Enabled=" + "true");
        this.config.addDefault("WoodenBucket_ItemID=" + woodenbucketIDs.getItemIDs().get(0));
        this.config.addDefault("bucketWorksInNether=" + "false");
    }
}
