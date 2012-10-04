package denoflionsx.plugins.FarmCraftory.Modules;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.Old.customFuel_OLD;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import denoflionsx.Old.baseModule;
import denoflionsx.Old.pluginBase;

public class Liquidmodule extends baseModule {

    public static ItemIDManager f = new ItemIDManager(2, "fruitjuice");
    public static ItemIDManager v = new ItemIDManager(2, "veggiejuice");
    public static customFuel_OLD fruitJuice;
    public static customFuel_OLD veggieJuice;

    public Liquidmodule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.addDefault("LiquidModule_Enabled=" + "true");
        this.addDefault("FruitJuice_Enabled=" + "true");
        this.addDefault("FruitJuice_AmountPerSqueeze=" + (1000 / 2));
        this.addDefault("FruitJuice_AmountPerSqueezeMulti=" + (1000 / 4));
        this.addDefault("FruitJuice_FermenterBonus=" + "2.0");
        this.addDefault("VeggieJuice_Enabled=" + "true");
        this.addDefault("VeggieJuice_AmountPerSqueezeSingle=" + (1000 / 4));
        this.addDefault("VeggieJuice_AmountPerSqueezeMulti=" + (1000 / 8));
        this.addDefault("VeggieJuice_FermenterBonus=" + "1.5");
        this.addDefault("FruitJuice_WoodenBucketAndBarrel=" + "true");
        this.addDefault("VeggieJuice_WoodenBucketAndBarrel=" + "true");
    }

    @Override
    protected void init() {
        if (this.getOptionBool("LiquidModule_Enabled")) {
            if (this.getOptionBool("FruitJuice_Enabled")) {
                fruitJuice = new customFuel_OLD("Fruit Juice", 1, 40000, customFuel_OLD.populateSprites(EnumLiquidTextures.Liquids.FRUITJUICE.getIndex()), f, PfFManagers.ColorManager.getColor(Colors.Values.MAUVE.toString()).convertRBG(), this.parent);
                if (this.getOptionBool("FruitJuice_WoodenBucketAndBarrel")){
                    PfFManagers.ContainerManager.addLiquid("Fruit Juice",PfFManagers.ItemManager.getItem("fruitjuice"),PfFManagers.ColorManager.getColor(Colors.Values.MAUVE.toString()));
                }
            }
            if (this.getOptionBool("VeggieJuice_Enabled")) {
                veggieJuice = new customFuel_OLD("Veggie Juice", 5, 2000, customFuel_OLD.populateSprites(EnumLiquidTextures.Liquids.VEGGIEJUICE.getIndex()), v, PfFManagers.ColorManager.getColor(Colors.Values.RED.toString()).convertRBG(), this.parent);
                if (this.getOptionBool("VeggieJuice_WoodenBucketAndBarrel")){
                    PfFManagers.ContainerManager.addLiquid("Veggie Juice", PfFManagers.ItemManager.getItem("veggiejuice"), PfFManagers.ColorManager.getColor(Colors.Values.RED.toString()));
                }
            }
        }
        recipes();
    }

    @Override
    protected void recipes() {
        if (this.getOptionBool("FruitJuice_Enabled")) {
            for (EnumCrops.TREE tree : EnumCrops.TREE.values()){
                SqueezerHelper.add(tree.getTree().getFruitItem(), new LiquidStack(PfFManagers.ItemManager.getItem("fruitjuice").itemID,this.getOptionInt("FruitJuice_AmountPerSqueeze")));
            }
            PfFManagers.FermenterManager.registerPfFLiquid(PfFManagers.ItemManager.getItem("fruitjuice"), this.getOptionFloat("FruitJuice_FermenterBonus"));   
        }
        if (this.getOptionBool("VeggieJuice_Enabled")) {
            for (EnumCrops.SINGLE crop : EnumCrops.SINGLE.values()) {
                SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("veggiejuice").itemID, this.getOptionInt("VeggieJuice_AmountPerSqueezeSingle")));
            }
            for (EnumCrops.MULTI crop : EnumCrops.MULTI.values()) {
                if (crop.equals(EnumCrops.MULTI.STRAWBERRY) || crop.equals(EnumCrops.MULTI.PINEAPPLE)) {
                    if (this.getOptionBool("FruitJuice_Enabled")) {
                        SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("fruitjuice").itemID, this.getOptionInt("FruitJuice_AmountPerSqueezeMulti")));
                    }
                } else {
                    SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("veggiejuice").itemID, this.getOptionInt("VeggieJuice_AmountPerSqueezeMulti")));
                }
            }
            PfFManagers.FermenterManager.registerPfFLiquid(PfFManagers.ItemManager.getItem("veggiejuice"), this.getOptionFloat("VeggieJuice_FermenterBonus"));
        }
    }

    public static void load(pluginBase parent) {
        baseModule b = new Liquidmodule(parent);
        b.register();
    }
}
