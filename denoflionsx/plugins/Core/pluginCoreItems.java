package denoflionsx.plugins.Core;

import denoflionsx.plugins.Core.Items.WoodenBucket;
import denoflionsx.plugins.Core.Items.MushroomBag;
import denoflionsx.plugins.Core.Items.ItemDebugStick;
import denoflionsx.plugins.Core.Items.LiquidVacuum;
import denoflionsx.plugins.Core.Items.MilkBag;
import denoflionsx.plugins.Core.Items.ItemExtractorTool;
import denoflionsx.plugins.Core.Items.DefaultReplacements.MushroomSoupBowlOverride;
import denoflionsx.plugins.Core.Items.MetaContainers.BarrelFuels;
import denoflionsx.plugins.Core.Items.MetaContainers.WoodenBucketFuels;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.Enums.EnumToolTextures;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.core;
import denoflionsx.Enums.Colors;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer;
import denoflionsx.plugins.Forestry.Utility.LiquidContainer.LiquidManagerWrapper;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.items.Fuels.customFuel;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;

public class pluginCoreItems extends PfFPluginTemplate {

    private ItemIDManager IDs = new ItemIDManager(3, "LiquidVacuum");
    private ItemIDManager woodenbucketIDs = new ItemIDManager(1, "WoodenBucket");
    private ItemIDManager mushroomSoupID = new ItemIDManager(2, "mushroomsoup");
    public static LiquidVacuum lv;
    public static MilkBag mb;
    public static MushroomBag mushroom;
    public static WoodenBucketFuels fuels;
    public static BarrelFuels bfuels;
    public static MushroomSoupBowlOverride soupBowl;
    public static WoodenBucket wb;
    public static customFuel mushroomSoup;
    public static ItemExtractorTool ex;

    public pluginCoreItems(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void recipes() {
        Item.itemsList[Item.potion.shiftedIndex].setMaxStackSize(this.config.getOptionInt("VanillaPotions_MaxStackSize"));
        if (this.config.getOptionBool("LiquidVacuum_Enabled")) {
            int BagSqueezeLeatherChance = this.config.getOptionInt("BagSqueezeLeatherChance");
            if (this.config.getOptionBool("MushroomBag_Enabled")) {
                LiquidVacuum.mushroombagEnabled = true;
                SqueezerHelper.add(PfFManagers.ItemManager.getItem("mushroombag"), new ItemStack(Item.leather), BagSqueezeLeatherChance, PfFManagers.ItemManager.getItem("mushroomsoup"), this.config.getOptionInt("MushroomBag_AmountPerSqueeze"));
                int temp = Item.bowlSoup.shiftedIndex;
                MushroomSoupBowlOverride.BowlStack = this.config.getOptionInt("SoupBowl_MaxStackSize");
                Item.itemsList[temp] = null;
                soupBowl = new MushroomSoupBowlOverride();
                LiquidManagerWrapper.registerLiquidContainer(new LiquidContainer(new LiquidStack(PfFManagers.ItemManager.getItem("mushroomsoup").itemID, 1000), new ItemStack(soupBowl), new ItemStack(Item.bowlEmpty)));
                PfFManagers.ContainerManager.addLiquid("Mushroom Soup", PfFManagers.ItemManager.getItem("mushroomsoup"), PfFManagers.ColorManager.getColor(Colors.Values.TAN.toString()));
            }
            SqueezerHelper.add(PfFManagers.ItemManager.getItem("milkbag"), new ItemStack(Item.leather), BagSqueezeLeatherChance, PfFManagers.ItemManager.getItem("milk"), this.config.getOptionInt("MilkBag_AmountPerSqueeze"));
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
        if (this.config.getOptionBool("ExtractorTool_Enabled")) {
            FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("extractortool"), new Object[]{
                        "IHI",
                        "XIX",
                        "IXI",
                        Character.valueOf('H'), PfFManagers.ItemManager.getItem("blacksmithhammer"),
                        Character.valueOf('I'), new ItemStack(Item.ingotIron)});
        }
    }

    @Override
    public void doSetup() {
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
            fuels = new WoodenBucketFuels(this.config.getOptionInt("WoodenBucketFuels_ItemID"), "woodenbucketfuels");
            PfFEvents.specialEvent.notifyListeners("Its a bucket!");
        }
        if (this.config.getOptionBool("BarrelFuels_getBarrelBack")) {
            BarrelFuels.mode = BarrelFuels.MODE.PERMA;
        }
        bfuels = new BarrelFuels(this.config.getOptionInt("BarrelFuels_ItemID"), "barrelfuels");
        PfFEvents.specialEvent.notifyListeners("Its a barrel!");
        if (this.config.getOptionBool("ExtractorTool_Enabled")) {
            ItemExtractorTool.ConfigMaxDamage = this.config.getOptionInt("ExtractorTool_MaxDamage");
            ex = new ItemExtractorTool(this.config.getOptionInt("ExtractorTool_ItemID"));
            PfFManagers.ExtractorTargetManager.addBlock(Block.glass);
            PfFManagers.ExtractorTargetManager.addBlock(Block.thinGlass);
        }
        if (this.config.getOptionBool("SmeltZombieFleshToLeather")) {
            ItemStack leather = new ItemStack(Item.leather);
            ItemStack zombie = new ItemStack(Item.rottenFlesh);
            FMLWrapper.MODE.FML.addSmelt(zombie, leather);
        }
    }

    @Override
    public void defaults() {
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
        this.config.addDefault("VanillaPotions_MaxStackSize=" + 10);
        this.config.addDefault("BarrelFuels_getBarrelBack=" + "false");
    }
}
