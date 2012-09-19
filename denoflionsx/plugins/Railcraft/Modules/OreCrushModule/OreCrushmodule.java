package denoflionsx.plugins.Railcraft.Modules.OreCrushModule;

import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Interfaces.IOreDictRecipeManager;
import denoflionsx.plugins.Railcraft.Managers.RockCrusherManager;
import denoflionsx.denLib.denLib;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.Railcraft.Managers.BlastFurnaceManager;
import denoflionsx.plugins.Railcraft.RockCrusherRecipeHelper;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import ic2.api.Ic2Recipes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreCrushmodule extends baseModule {

    public static multiItem dusts;
    public static ItemIDManager ids = new ItemIDManager(1, "dusts");
    public static IOreDictRecipeManager RockCrusherManager = new RockCrusherManager();
    public static IOreDictRecipeManager BlastFurnaceManager = new BlastFurnaceManager();
    public static int NumberOfDustPerCrush;
    public static MODE mode = MODE.PFF;

    public OreCrushmodule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("OreCrushModule_Enabled=" + "true");
        this.addDefault("Dusts_ItemID=" + ids.getItemIDs().get(0));
        this.addDefault("NumberOfDustPerCrush=" + 2);
        this.addDefault("KillIC2Macerator=" + "true");
        this.addDefault("ForcePFFMode=" + "false");
    }

    @Override
    protected void init() {
        if (this.getOptionBool("OreCrushModule_Enabled")) {
            if (denLib.detect(EnumModIDs.MODS.IC2.getID()) && !this.getOptionBool("ForcePFFMode")) {
                mode = MODE.IC2;
            }
            NumberOfDustPerCrush = this.getOptionInt("NumberOfDustPerCrush");
            if (mode.equals(MODE.PFF)) {
                PFFMode();
            } else if (mode.equals(MODE.IC2)) {
                IC2Mode();
            }
            recipes();
        }
    }

    @Override
    protected void recipes() {
    }

    public void PFFMode() {
        EnumDustTextures.DUST.init();
        dusts = new multiItem(this.getOptionInt("Dusts_ItemID"), "dusts");
        dusts.add("Iron Dust", 0, EnumDustTextures.DUST.IRON.getIndex());
        dusts.add("Gold Dust", 1, EnumDustTextures.DUST.GOLD.getIndex());
        dusts.add("Copper Dust", 2, EnumDustTextures.DUST.COPPER.getIndex());
        dusts.add("Tin Dust", 3, EnumDustTextures.DUST.TIN.getIndex());
        dusts.add("Silver Dust", 4, EnumDustTextures.DUST.SILVER.getIndex());
        dusts.add("Coal Dust", 5, EnumDustTextures.DUST.COAL.getIndex());

        ArrayList<ItemStack> ironOre = new ArrayList();
        ironOre.add(new ItemStack(Block.oreIron));
        ArrayList<ItemStack> goldOre = new ArrayList();
        goldOre.add(new ItemStack(Block.oreGold));
        ArrayList<ItemStack> coalOre = new ArrayList();
        coalOre.add(new ItemStack(Item.coal, 1, 0));

        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("irondust", NumberOfDustPerCrush), ironOre);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("golddust", NumberOfDustPerCrush), goldOre);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("copperdust",NumberOfDustPerCrush), OreDictionary.getOres("oreCopper"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("tindust",NumberOfDustPerCrush), OreDictionary.getOres("oreTin"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("silverdust",NumberOfDustPerCrush), OreDictionary.getOres("oreSilver"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("coaldust",NumberOfDustPerCrush), coalOre);

        ArrayList<ItemStack> ironBar = new ArrayList();
        ironBar.add(new ItemStack(Item.ingotIron));
        ArrayList<ItemStack> goldBar = new ArrayList();
        goldBar.add(new ItemStack(Item.ingotGold));

        BlastFurnaceManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("irondust",1), ironBar);
        BlastFurnaceManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("golddust",1), goldBar);
        BlastFurnaceManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("copperdust",1), OreDictionary.getOres("ingotCopper"));
        BlastFurnaceManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("tindust",1), OreDictionary.getOres("ingotTin"));
        BlastFurnaceManager.addRecipes(PfFManagers.ItemManager.getNewItemStack("silverdust",1), OreDictionary.getOres("ingotSilver"));

        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getItem("irondust"), ironBar);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getItem("golddust"), goldBar);
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getItem("tindust"), OreDictionary.getOres("ingotTin"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getItem("copperdust"), OreDictionary.getOres("ingotCopper"));
        RockCrusherManager.addRecipes(PfFManagers.ItemManager.getItem("silverdust"), OreDictionary.getOres("ingotSilver"));
    }

    public void IC2Mode() {
        if (this.getOptionBool("KillIC2Macerator")) {
            Iterator i = Ic2Recipes.getMaceratorRecipes().iterator();
            while (i.hasNext()) {
                Map.Entry pairs = (Map.Entry) i.next();
                ItemStack input = (ItemStack) pairs.getKey();
                ItemStack output = (ItemStack) pairs.getValue();
                RockCrusherRecipeHelper.Recipe r = new RockCrusherRecipeHelper.Recipe(input);
                r.addOutput(output, 1.0f);
                RockCrusherRecipeHelper.add(r);
            }
            Ic2Recipes.getMaceratorRecipes().clear();
        }
    }

    public static void load(pluginBase parent) {
        baseModule b = new OreCrushmodule(parent);
        b.register();
    }

    public static enum MODE {

        PFF,
        IC2;
    }
}
