package denoflionsx.plugins;

import denoflionsx.API.PfFManagers;
import forestry.api.core.ItemInterface;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.core.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.denLib.Colors;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Core.*;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import denoflionsx.plugins.Forestry.SqueezerWrapper;
import denoflionsx.plugins.Forestry.pipette;

public class pluginCoreItems extends pluginBase {

    private ItemIDManager IDs = new ItemIDManager(3, "LiquidVacuum");
    private ItemIDManager woodenbucketIDs = new ItemIDManager(1, "WoodenBucket");
    private ItemIDManager mushroomSoupID = new ItemIDManager(2,"mushroomsoup");
    private LiquidVacuum lv;
    private MilkBag mb;
    private MushroomBag mushroom;
    private WoodenBucketFuels fuels;
    public static WoodenBucket wb;
    private customFuel mushroomSoup;

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
        SqueezerWrapper.add(PfFManagers.ItemManager.getItem("milkbag"), new ItemStack(Item.leather), 10, PfFManagers.ItemManager.getItem("milk"), this.config.getOptionInt("MilkBag_AmountPerSqueeze"));
        SqueezerWrapper.add(PfFManagers.ItemManager.getItem("mushroombag"),new ItemStack(Item.leather),10,PfFManagers.ItemManager.getItem("mushroomsoup"),this.config.getOptionInt("MushroomBag_AmountPerSqueeze"));
        FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("liquidvacuum"), new Object[]{
                    "LLL",
                    "SIb",
                    "SIB",
                    Character.valueOf('L'), new ItemStack(Item.leather),
                    Character.valueOf('S'), new ItemStack(Item.silk),
                    Character.valueOf('I'), new ItemStack(Item.ingotIron),
                    Character.valueOf('b'), new ItemStack(Item.glassBottle),
                    Character.valueOf('G'), ItemInterface.getItem("gearBronze"),
                    Character.valueOf('B'), new ItemStack(Item.bucketEmpty)});
        FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("woodenbucket"), new Object[]{
                    "XXX",
                    "WXW",
                    "XWX",
                    Character.valueOf('W'), Block.wood});
        FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("woodenbucket"), new Object[]{
                    "WXW",
                    "XWX",
                    "XXX",
                    Character.valueOf('W'), Block.wood});
    }

    @Override
    protected boolean init() {
        this.hooked = true;
        if (this.config.getOptionBool("MushroomBag_Enabled")) {
            mushroom = new MushroomBag(this.config.getOptionInt("MushroomBag_ItemID"));
            LiquidVacuum.mushroombag = new ItemStack(mushroom, 1, 0);
            LiquidVacuum.mushroombagEnabled = this.config.getOptionBool("MushroomBag_Enabled");
            mushroomSoup = new customFuel("Mushroom Soup",1,40000,customFuel.populateSprites(EnumLiquidTextures.Liquids.MUSHROOMSOUP.getIndex()),mushroomSoupID,Colors.Values.TAN.getColor(),this);
        }
        if (this.config.getOptionBool("LiquidVacuum_Enabled")) {
            mb = new MilkBag(this.config.getOptionInt("MilkBag_ItemID"), "milkbag");
            mb.add("milkbag", 0, EnumToolTextures.ToolTextures.MILKBAG.getIndex(), "Milk Bag");
            LiquidVacuum.milkbag = new ItemStack(mb, 1, 0);

            lv = new LiquidVacuum(this.config.getOptionInt("LiquidVacuum_ItemID"), "liquidvacuum");
            lv.add("liquidvacuum", 0, EnumToolTextures.ToolTextures.LIQUIDVACUUM.getIndex(), "Liquid Vacuum");
        }
        if (this.config.getOptionBool("WoodenBucket_Enabled")) {
            wb = new WoodenBucket(this.config.getOptionInt("WoodenBucket_ItemID"), "woodenbucket");
            wb.add("woodenbucket", wb.metaMap.get("Wooden Bucket"), EnumToolTextures.ToolTextures.WOODENBUCKET_EMPTY.getIndex(), "Wooden Bucket");
            wb.add("filledwoodenbucket", wb.metaMap.get("Filled Wooden Bucket"), EnumToolTextures.ToolTextures.WOODENBUCKET_FULL.getIndex(), "Filled Wooden Bucket");
            WoodenBucket.bucketWorksInNether = this.config.getOptionBool("bucketWorksInNether");
            if (core.isBetaBuild) {
                ItemDebugStick.Debug.create();
            }
            fuels = new WoodenBucketFuels(this.getOptionInt("WoodenBucketFuels_ItemID"), "woodenbucketfuels");
        }
        if (this.config.getOptionBool("SmeltZombieFleshToLeather")) {
            ItemStack leather = new ItemStack(Item.leather);
            ItemStack zombie = new ItemStack(Item.rottenFlesh);
            FMLWrapper.MODE.FML.addSmelt(zombie, leather);
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
        this.config.addDefault("MushroomBag_Enabled=" + "true");
        this.config.addDefault("MushroomBag_ItemID=" + IDs.getItemIDs().get(2));
        this.config.addDefault("MushroomBag_AmountPerSqueeze=" + 1000);
        this.config.addDefault("WoodenBucket_Enabled=" + "true");
        this.config.addDefault("WoodenBucket_ItemID=" + woodenbucketIDs.getItemIDs().get(0));
        this.config.addDefault("WoodenBucketFuels_ItemID=" + WoodenBucketFuels.ID.getItemIDs().get(0));
        this.config.addDefault("SmeltZombieFleshToLeather=" + "true");
        this.config.addDefault("bucketWorksInNether=" + "false");
    }
}
