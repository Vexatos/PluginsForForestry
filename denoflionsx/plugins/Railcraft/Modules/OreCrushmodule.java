package denoflionsx.plugins.Railcraft.Modules;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.plugins.Railcraft.Modules.OreDicRecipeManager.IOreDictRecipeManager;
import denoflionsx.plugins.Railcraft.Modules.OreDicRecipeManager.RockCrusherManager;
import denoflionsx.plugins.Railcraft.Modules.OreDicRecipeManager.SmeltManager;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreCrushmodule extends baseModule {

    public static DustItem iron;
    public static DustItem gold;
    public static DustItem copper;
    public static DustItem tin;
    public static DustItem silver;
    public static DustItem coal;
    public static ItemIDManager ids = new ItemIDManager(6, "dusts");
    public static IOreDictRecipeManager RockCrusherManager = new RockCrusherManager();
    public static IOreDictRecipeManager SmeltManager = new SmeltManager();
    public static int NumberOfDustPerCrush;

    public OreCrushmodule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("OreCrushModule_Enabled=" + "true");
        this.addDefault("IronDust_ItemID=" + ids.getItemIDs().get(0));
        this.addDefault("GoldDust_ItemID=" + ids.getItemIDs().get(1));
        this.addDefault("CopperDust_ItemID=" + ids.getItemIDs().get(2));
        this.addDefault("TinDust_ItemID=" + ids.getItemIDs().get(3));
        this.addDefault("SilverDust_ItemID=" + ids.getItemIDs().get(4));
        this.addDefault("CoalDust_ItemID=" + ids.getItemIDs().get(5));
        this.addDefault("CoalDust_AllowCharcoal=" + "false");
        this.addDefault("NumberOfDustPerCrush=" + 2);
    }

    @Override
    protected void init() {
        if (this.getOptionBool("OreCrushModule_Enabled")) {
            EnumDustTextures.DUST.init();
            iron = new DustItem(this.getOptionInt("IronDust_ItemID"), "Iron Dust", EnumDustTextures.DUST.IRON);
            gold = new DustItem(this.getOptionInt("GoldDust_ItemID"), "Gold Dust", EnumDustTextures.DUST.GOLD);
            copper = new DustItem(this.getOptionInt("CopperDust_ItemID"), "Copper Dust", EnumDustTextures.DUST.COPPER);
            tin = new DustItem(this.getOptionInt("TinDust_ItemID"), "Tin Dust", EnumDustTextures.DUST.TIN);
            silver = new DustItem(this.getOptionInt("SilverDust_ItemID"), "Silver Dust", EnumDustTextures.DUST.SILVER);
            coal = new DustItem(this.getOptionInt("CoalDust_ItemID"), "Coal Dust", EnumDustTextures.DUST.COAL);
            NumberOfDustPerCrush = this.getOptionInt("NumberOfDustPerCrush");
            recipes();
        }
    }

    @Override
    protected void recipes() {
        ArrayList<ItemStack> ironOre = new ArrayList();
        ironOre.add(new ItemStack(Block.oreIron));
        ArrayList<ItemStack> goldOre = new ArrayList();
        goldOre.add(new ItemStack(Block.oreGold));
        ArrayList<ItemStack> coalOre = new ArrayList();
        coalOre.add(new ItemStack(Item.coal, 1, 0));
        if (this.getOptionBool("CoalDust_AllowCharcoal")) {
            coalOre.add(new ItemStack(Item.coal, 1, 1));
        }
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("irondust", NumberOfDustPerCrush), ironOre);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("golddust", NumberOfDustPerCrush), goldOre);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("copperdust", NumberOfDustPerCrush), OreDictionary.getOres("oreCopper"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("tindust", NumberOfDustPerCrush), OreDictionary.getOres("oreTin"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("silverdust", NumberOfDustPerCrush), OreDictionary.getOres("oreSilver"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("coaldust", NumberOfDustPerCrush), coalOre);

        ArrayList<ItemStack> ironBar = new ArrayList();
        ironBar.add(new ItemStack(Item.ingotIron));
        ArrayList<ItemStack> goldBar = new ArrayList();
        goldBar.add(new ItemStack(Item.ingotGold));

        SmeltManager.addRecipes(PfFManagers.ItemManager.getItem("irondust"), ironBar);
        SmeltManager.addRecipes(PfFManagers.ItemManager.getItem("golddust"), goldBar);
        SmeltManager.addRecipes(PfFManagers.ItemManager.getItem("copperdust"), OreDictionary.getOres("ingotCopper"));
        SmeltManager.addRecipes(PfFManagers.ItemManager.getItem("tindust"), OreDictionary.getOres("ingotTin"));
        SmeltManager.addRecipes(PfFManagers.ItemManager.getItem("silverdust"), OreDictionary.getOres("ingotSilver"));
    }

    public static void load(pluginBase parent) {
        baseModule b = new OreCrushmodule(parent);
        b.register();
    }
}
