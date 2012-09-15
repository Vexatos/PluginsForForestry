package denoflionsx.plugins;

import denoflionsx.API.Enums.EnumAnimals;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Config.Config;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.plugins.BluesFood.*;
import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class pluginBlueFood extends pluginBase {

    ItemButcherKnife knife;
    ItemIDManager knifeid = new ItemIDManager(1, "ButcherKnife");
    ItemIDManager foodid = new ItemIDManager(11, "food");
    multiItemFood flour;
    ItemFoods RawSausage;
    ItemFoods GroundBeef;
    ItemFoods Tentacle;
    ItemFoods LambChop;
    ItemFoods FriedEgg;
    ItemFoods CookedSausage;
    ItemFoods Hamburger;
    ItemFoods Calamari;
    ItemFoods CookedLambChops;
    Cupcake CupCake;

    public pluginBlueFood() {
        this.name = "pluginBlueFood";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    protected void defaults() {
        this.config.addDefault("[Blue's Food Plugin Options]");
        this.config.addDefault("BlueFood_Enabled=" + "true");
        this.config.addDefault("ButcherKnife_ItemID=" + knifeid.getItemIDs().get(0));
        this.config.addDefault("FriedEgg_ItemID=" + this.foodid.getItemIDs().get(0));
        this.config.addDefault("CookedSausage_ItemID=" + this.foodid.getItemIDs().get(1));
        this.config.addDefault("Hamburger_ItemID=" + this.foodid.getItemIDs().get(2));
        this.config.addDefault("Calamari_ItemID=" + this.foodid.getItemIDs().get(3));
        this.config.addDefault("CookedLambChop_ItemID=" + this.foodid.getItemIDs().get(4));
        this.config.addDefault("Cupcake_ItemID=" + this.foodid.getItemIDs().get(5));
        this.config.addDefault("RawSausage_ItemID=" + this.foodid.getItemIDs().get(6));
        this.config.addDefault("GroundBeef_ItemID=" + this.foodid.getItemIDs().get(7));
        this.config.addDefault("Tentacle_ItemID=" + this.foodid.getItemIDs().get(8));
        this.config.addDefault("LambChop_ItemID=" + this.foodid.getItemIDs().get(9));
        this.config.addDefault("Flour_ItemID=" + this.foodid.getItemIDs().get(10));
    }

    @Override
    protected boolean init() {
        if (!this.getOptionBool("BlueFood_Enabled")) {
            return false;
        }
        this.hooked = true;
        knife = new ItemButcherKnife(this.getOptionInt("ButcherKnife_ItemID"), EnumFoodTextures.FOOD.BUTCHER_KNIFE.getIndex(), 6, 500, "Butcher Knife");

        this.FriedEgg = new ItemFoods(this.getOptionInt("FriedEgg_ItemID"), EnumFoodTextures.FOOD.FRIED_EGG.getIndex(), 8, 10.0f, "Fried Egg");
        this.CookedSausage = new ItemFoods(this.getOptionInt("CookedSausage_ItemID"), EnumFoodTextures.FOOD.SAUSAGE_COOKED.getIndex(), 8, 10.0f, "Sausage");
        this.Hamburger = new ItemFoods(this.getOptionInt("Hamburger_ItemID"), EnumFoodTextures.FOOD.HAMBURGER.getIndex(), 8, 10.0f, "Hamburger");
        this.Calamari = new ItemFoods(this.getOptionInt("Calamari_ItemID"), EnumFoodTextures.FOOD.CALAMARI.getIndex(), 8, 10.0f, "Calamari");
        this.CookedLambChops = new ItemFoods(this.getOptionInt("CookedLambChop_ItemID"), EnumFoodTextures.FOOD.COOKED_LAMBCHOP.getIndex(), 8, 10.0f, "Cooked Lambchop");
        this.CupCake = new Cupcake(this.getOptionInt("Cupcake_ItemID"), EnumFoodTextures.FOOD.CUPCAKE.getIndex(), 4, 6.0f, "Cupcake");
        this.RawSausage = new ItemFoods(this.getOptionInt("RawSausage_ItemID"), EnumFoodTextures.FOOD.SAUSAGE.getIndex(), 1, 1.2f, "Raw Sausage");
        this.GroundBeef = new ItemFoods(this.getOptionInt("GroundBeef_ItemID"), EnumFoodTextures.FOOD.GROUND_BEEF.getIndex(), 1, 1.2f, "Ground Beef");
        this.Tentacle = new ItemFoods(this.getOptionInt("Tentacle_ItemID"), EnumFoodTextures.FOOD.TENTACLE.getIndex(), 1, 1.2f, "Tentacle");
        this.LambChop = new ItemFoods(this.getOptionInt("LambChop_ItemID"), EnumFoodTextures.FOOD.LAMBCHOP.getIndex(), 1, 1.2f, "Lambchop");
        this.flour = new multiItemFood(this.getOptionInt("Flour_ItemID"), "flour");
        this.flour.add("Flour", 0, EnumFoodTextures.FOOD.FLOUR.getIndex());
        return this.hooked;
    }

    @Override
    protected void recipes() {
        if (this.getOptionBool("BlueFood_Enabled")) {
            FMLWrapper.MODE.FML.addSmelt(new ItemStack(Item.egg), PfFManagers.ItemManager.getItem("friedegg"));
            PfFManagers.ButcherKnifeManager.addDropToAnimal(EnumAnimals.ANIMALS.PIG, PfFManagers.ItemManager.getItem("rawsausage"));
            FMLWrapper.MODE.FML.addSmelt(PfFManagers.ItemManager.getItem("rawsausage"), PfFManagers.ItemManager.getItem("sausage"));
            PfFManagers.ButcherKnifeManager.addDropToAnimal(EnumAnimals.ANIMALS.COW, PfFManagers.ItemManager.getItem("groundbeef"));
            FMLWrapper.MODE.FML.addSmelt(PfFManagers.ItemManager.getItem("groundbeef"), PfFManagers.ItemManager.getItem("hamburger"));
            PfFManagers.ButcherKnifeManager.addDropToAnimal(EnumAnimals.ANIMALS.SQUID, PfFManagers.ItemManager.getItem("tentacle"));
            FMLWrapper.MODE.FML.addSmelt(PfFManagers.ItemManager.getItem("tentacle"), PfFManagers.ItemManager.getItem("calamari"));
            PfFManagers.ButcherKnifeManager.addDropToAnimal(EnumAnimals.ANIMALS.SHEEP, PfFManagers.ItemManager.getItem("lambchop"));
            FMLWrapper.MODE.FML.addSmelt(PfFManagers.ItemManager.getItem("lambchop"), PfFManagers.ItemManager.getItem("cookedlambchop"));
            FMLWrapper.MODE.FML.addShapelessRecipe(PfFManagers.ItemManager.getItem("flour"), new Object[]{new ItemStack(Item.wheat)});
            ArrayList<ItemStack> containers = PfFManagers.ItemManager.getAllContainersForLiquid("milk");
            containers.add(new ItemStack(Item.bucketMilk));
            for (ItemStack i : containers) {
                if (!i.getItem().getItemNameIS(i).toLowerCase().contains("barrel")) {
                    FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getNewItemStack("cupcake", 4), new Object[]{
                                "MMM",
                                "SES",
                                "FFF",
                                Character.valueOf('M'), i,
                                Character.valueOf('S'), Item.sugar,
                                Character.valueOf('E'), Item.egg,
                                Character.valueOf('F'), PfFManagers.ItemManager.getItem("flour")});
                }
            }
            FMLWrapper.MODE.FML.addRecipe(PfFManagers.ItemManager.getItem("butcherknife"), new Object[]{
                        "XHX",
                        "SII",
                        "XII",
                        Character.valueOf('H'), PfFManagers.ItemManager.getItem("blacksmithhammer"),
                        Character.valueOf('S'), new ItemStack(Item.stick),
                        Character.valueOf('I'), new ItemStack(Item.ingotIron)});
        }
    }
}
