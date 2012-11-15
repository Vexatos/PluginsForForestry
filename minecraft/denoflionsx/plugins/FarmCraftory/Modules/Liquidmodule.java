package denoflionsx.plugins.FarmCraftory.Modules;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EnumEventSpecialMessages;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.PfFEvents;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.Colors;
import denoflionsx.core.ItemIDManager;
import denoflionsx.Enums.EnumLiquidTextures;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.plugins.Forestry.Helpers.SqueezerHelper;
import denoflionsx.core.PfFModuleTemplate;
import denoflionsx.items.Fuels.customFuel;
import net.minecraftforge.liquids.LiquidStack;

public class Liquidmodule extends PfFModuleTemplate {

    public static ItemIDManager f = new ItemIDManager(2, "fruitjuice");
    public static ItemIDManager v = new ItemIDManager(2, "veggiejuice");
    public static customFuel fruitJuice;
    public static customFuel veggieJuice;

    public Liquidmodule(String name, String parent) {
        super(name, parent);
        PfFEvents.specialEvent.register(this);
    }

    @Override
    public void defaults() {
        this.config.addDefault("LiquidModule_Enabled=" + "true");
        this.config.addDefault("FruitJuice_Enabled=" + "true");
        this.config.addDefault("FruitJuice_AmountPerSqueeze=" + (1000 / 2));
        this.config.addDefault("FruitJuice_AmountPerSqueezeMulti=" + (1000 / 4));
        this.config.addDefault("FruitJuice_FermenterBonus=" + "2.0");
        this.config.addDefault("VeggieJuice_Enabled=" + "true");
        this.config.addDefault("VeggieJuice_AmountPerSqueezeSingle=" + (1000 / 4));
        this.config.addDefault("VeggieJuice_AmountPerSqueezeMulti=" + (1000 / 8));
        this.config.addDefault("VeggieJuice_FermenterBonus=" + "1.5");
        this.config.addDefault("FruitJuice_WoodenBucketAndBarrel=" + "true");
        this.config.addDefault("VeggieJuice_WoodenBucketAndBarrel=" + "true");
    }

    @PfFSubscribe(Event = PfFEventTypes.SPECIAL)
    public void barrel(EventSpecial event) {
        if (!event.getMessage().equals(EnumEventSpecialMessages.BARREL.getMsg())) {
            return;
        }
        if (this.config.getOptionBool("FruitJuice_WoodenBucketAndBarrel")) {
            PfFManagers.ContainerManager.addLiquid("Fruit Juice", PfFManagers.ItemManager.getItem("fruitjuice"), PfFManagers.ColorManager.getColor(Colors.Values.MAUVE.toString()));
        }
        if (this.config.getOptionBool("VeggieJuice_WoodenBucketAndBarrel")) {
            PfFManagers.ContainerManager.addLiquid("Veggie Juice", PfFManagers.ItemManager.getItem("veggiejuice"), PfFManagers.ColorManager.getColor(Colors.Values.RED.toString()));
        }
    }

    @Override
    public void doSetup() {
        if (this.config.getOptionBool("FruitJuice_Enabled")) {
            fruitJuice = new customFuel("Fruit Juice", 1, 40000, customFuel.populateSprites(EnumLiquidTextures.Liquids.FRUITJUICE.getIndex()), f, PfFManagers.ColorManager.getColor(Colors.Values.MAUVE.toString()).convertRBG(), this);
        }
        if (this.config.getOptionBool("VeggieJuice_Enabled")) {
            veggieJuice = new customFuel("Veggie Juice", 5, 2000, customFuel.populateSprites(EnumLiquidTextures.Liquids.VEGGIEJUICE.getIndex()), v, PfFManagers.ColorManager.getColor(Colors.Values.RED.toString()).convertRBG(), this);
        }
    }

    @Override
    public void recipes() {
        if (this.config.getOptionBool("FruitJuice_Enabled")) {
            for (EnumCrops.TREE tree : EnumCrops.TREE.values()) {
                SqueezerHelper.add(tree.getTree().getFruitItem(), new LiquidStack(PfFManagers.ItemManager.getItem("fruitjuice").itemID, this.config.getOptionInt("FruitJuice_AmountPerSqueeze")));
            }
            PfFManagers.FermenterManager.registerPfFLiquid(PfFManagers.ItemManager.getItem("fruitjuice"), this.config.getOptionFloat("FruitJuice_FermenterBonus"));
        }
        if (this.config.getOptionBool("VeggieJuice_Enabled")) {
            for (EnumCrops.SINGLE crop : EnumCrops.SINGLE.values()) {
                SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("veggiejuice").itemID, this.config.getOptionInt("VeggieJuice_AmountPerSqueezeSingle")));
            }
            for (EnumCrops.MULTI crop : EnumCrops.MULTI.values()) {
                if (crop.equals(EnumCrops.MULTI.STRAWBERRY) || crop.equals(EnumCrops.MULTI.PINEAPPLE)) {
                    if (this.config.getOptionBool("FruitJuice_Enabled")) {
                        SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("fruitjuice").itemID, this.config.getOptionInt("FruitJuice_AmountPerSqueezeMulti")));
                    }
                } else {
                    SqueezerHelper.add(crop.getPlant().getPlant(), new LiquidStack(PfFManagers.ItemManager.getItem("veggiejuice").itemID, this.config.getOptionInt("VeggieJuice_AmountPerSqueezeMulti")));
                }
            }
            PfFManagers.FermenterManager.registerPfFLiquid(PfFManagers.ItemManager.getItem("veggiejuice"), this.config.getOptionFloat("VeggieJuice_FermenterBonus"));
        }
    }
}
