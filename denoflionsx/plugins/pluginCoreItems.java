package denoflionsx.plugins;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.EnumModIDs;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.core.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.denLib.Colors;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Core.*;
import denoflionsx.plugins.Forestry.LiquidContainer;
import denoflionsx.plugins.Forestry.LiquidContainer.LiquidManagerWrapper;
import denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import denoflionsx.plugins.Forestry.SqueezerWrapper;

public class pluginCoreItems extends pluginBase {

    private ItemIDManager IDs = new ItemIDManager(3, "LiquidVacuum");
    private ItemIDManager woodenbucketIDs = new ItemIDManager(1, "WoodenBucket");
    private ItemIDManager mushroomSoupID = new ItemIDManager(2, "mushroomsoup");
    private LiquidVacuum lv;
    private MilkBag mb;
    private MushroomBag mushroom;
    private WoodenBucketFuels fuels;
    private BarrelFuels bfuels;
    private MushroomSoupBowlOverride soupBowl;
    public static WoodenBucket wb;
    private customFuel mushroomSoup;
    private ItemExtractorTool ex;

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
        if (this.config.getOptionBool("LiquidVacuum_Enabled")) {
            int BagSqueezeLeatherChance = this.config.getOptionInt("BagSqueezeLeatherChance");
            if (this.config.getOptionBool("MushroomBag_Enabled")) {
                LiquidVacuum.mushroombagEnabled = true;
                SqueezerWrapper.add(PfFManagers.ItemManager.getItem("mushroombag"), new ItemStack(Item.leather), BagSqueezeLeatherChance, PfFManagers.ItemManager.getItem("mushroomsoup"), this.config.getOptionInt("MushroomBag_AmountPerSqueeze"));
                int temp = Item.bowlSoup.shiftedIndex;
                MushroomSoupBowlOverride.BowlStack = this.config.getOptionInt("SoupBowl_MaxStackSize");
                Item.itemsList[temp] = null;
                soupBowl = new MushroomSoupBowlOverride();
                LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(PfFManagers.ItemManager.getItem("mushroomsoup").itemID, 1000), new ItemStack(soupBowl), new ItemStack(Item.bowlEmpty)));
            }
            SqueezerWrapper.add(PfFManagers.ItemManager.getItem("milkbag"), new ItemStack(Item.leather), BagSqueezeLeatherChance, PfFManagers.ItemManager.getItem("milk"), this.config.getOptionInt("MilkBag_AmountPerSqueeze"));
            LiquidVacuum.Recipes.useBCRecipe = this.config.getOptionBool("LiquidVacuum_UseBCRecipeIfAvailable");
            LiquidVacuum.Recipes.isRecipeCheap = this.config.getOptionBool("LiquidVacuum_EnableCheapRecipe");
            // Recipe Stuff.
            if (denLib.detect(EnumModIDs.MODS.BUILDCRAFT_CORE.getID()) && denLib.detect(EnumModIDs.MODS.BUILDCRAFT_FACTORY.getID()) && !LiquidVacuum.Recipes.isRecipeCheap && LiquidVacuum.Recipes.useBCRecipe) {
                FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("liquidvacuum"), LiquidVacuum.Recipes.getBCExpensiveRecipe());
            } else if (LiquidVacuum.Recipes.isRecipeCheap) {
                FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("liquidvacuum"), LiquidVacuum.Recipes.getCheapRecipe());
            } else if (!denLib.detect(EnumModIDs.MODS.BUILDCRAFT_CORE.getID()) && !denLib.detect(EnumModIDs.MODS.BUILDCRAFT_FACTORY.getID()) && !LiquidVacuum.Recipes.isRecipeCheap) {
                FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("liquidvacuum"), LiquidVacuum.Recipes.getExpensiveRecipe());
            }
        }

        if (this.config.getOptionBool("WoodenBucket_Enabled")) {
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
    }

    @Override
    protected boolean init() {
        this.hooked = true;
        if (this.config.getOptionBool("MushroomBag_Enabled")) {
            mushroom = new MushroomBag(this.config.getOptionInt("MushroomBag_ItemID"));
            LiquidVacuum.mushroombagEnabled = this.config.getOptionBool("MushroomBag_Enabled");
            mushroomSoup = new customFuel("Mushroom Soup", 1, 40000, customFuel.populateSprites(EnumLiquidTextures.Liquids.MUSHROOMSOUP.getIndex()), mushroomSoupID, Colors.Values.TAN.getColor(), this);
        }
        if (this.config.getOptionBool("LiquidVacuum_Enabled")) {
            mb = new MilkBag(this.config.getOptionInt("MilkBag_ItemID"), "milkbag");
            mb.add("milkbag", 0, EnumToolTextures.ToolTextures.MILKBAG.getIndex(), "Milk Bag");

            lv = new LiquidVacuum(this.config.getOptionInt("LiquidVacuum_ItemID"));

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
        bfuels = new BarrelFuels(this.getOptionInt("BarrelFuels_ItemID"),"barrelfuels");
        if (this.config.getOptionBool("ExtractorTool_Enabled")) {
            ItemExtractorTool.ConfigMaxDamage = this.config.getOptionInt("ExtractorTool_MaxDamage");
            ex = new ItemExtractorTool(this.config.getOptionInt("ExtractorTool_ItemID"));
            PfFManagers.ExtractorTargetManager.addBlock(Block.glass);
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
        this.config.addDefault("LiquidVacuum_UseBCRecipeIfAvailable=" + "true");
        this.config.addDefault("LiquidVacuum_EnableCheapRecipe=" + "false");
        this.config.addDefault("MilkBag_ItemID=" + IDs.getItemIDs().get(1));
        this.config.addDefault("MilkBag_AmountPerSqueeze=" + 1000);
        this.config.addDefault("MushroomBag_Enabled=" + "true");
        this.config.addDefault("MushroomBag_ItemID=" + IDs.getItemIDs().get(2));
        this.config.addDefault("MushroomBag_AmountPerSqueeze=" + 1000);
        this.config.addDefault("SoupBowl_MaxStackSize=" + 1);
        this.config.addDefault("WoodenBucket_Enabled=" + "true");
        this.config.addDefault("WoodenBucket_ItemID=" + woodenbucketIDs.getItemIDs().get(0));
        this.config.addDefault("WoodenBucketFuels_ItemID=" + WoodenBucketFuels.ID.getItemIDs().get(0));
        this.config.addDefault("BarrelFuels_ItemID=" + BarrelFuels.ID.getItemIDs().get(0));
        this.config.addDefault("SmeltZombieFleshToLeather=" + "true");
        this.config.addDefault("BagSqueezeLeatherChance=" + 10);
        this.config.addDefault("bucketWorksInNether=" + "false");
        ItemExtractorTool.ID = new ItemIDManager(1, "ExtractorTool");
        this.config.addDefault("ExtractorTool_Enabled=" + "true");
        this.config.addDefault("ExtractorTool_ItemID=" + ItemExtractorTool.ID.getItemIDs().get(0));
        this.config.addDefault("ExtractorTool_MaxDamage=" + ItemExtractorTool.ConfigMaxDamage);
    }
}
