package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.denoflionsx.core.ItemIDManager;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.items.Placeholder;
import net.minecraft.src.denoflionsx.plugins.BluesFood.*;

public class pluginbluesFood extends pluginBase {

    ItemButcherKnife knife;
    ItemIDManager knifeid = new ItemIDManager(1, "ButcherKnife");
    ItemFoods food;
    ItemFoodRaw rawfood;
    ItemIDManager foodid = new ItemIDManager(2, "Food");
    MachineOven oven = new MachineOven();

    public pluginbluesFood() {
        this.name = "pluginbluesFood";
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
    protected void defaults() {
        this.config.addDefault("[Blue's Food Plugin Options]");
        this.config.addDefault("ButcherKnife_ItemID=" + knifeid.getItemIDs().get(0));
        this.config.addDefault("Sausage_ItemID=" + foodid.getItemIDs().get(0));
        this.config.addDefault("RawFood_ItemID=" + foodid.getItemIDs().get(1));
    }

    @Override
    protected boolean init() {
        this.hooked = true;
        knife = new ItemButcherKnife(this.config.getOptionInt("ButcherKnife_ItemID"), EnumFoodTextures.FOOD.BUTCHER_KNIFE.getIndex(), 6, 500, "Butcher Knife");
        rawfood = new ItemFoodRaw(this.config.getOptionInt("RawFood_ItemID"), "rawfoods");
        rawfood.metaMap.put("PLACEHOLDER", 0);
        rawfood.metaMap.put("Cooked Sausage", 1);
        rawfood.add("placeholder", Placeholder.Sprite.RADICAL_EDWARD.getIndex(), 0, "PLACEHOLDER");
        rawfood.add("cookedsausage", 1, EnumFoodTextures.FOOD.SAUSAGE_COOKED.getIndex(), "Cooked Sausage");
        food = new ItemFoods(this.config.getOptionInt("Sausage_ItemID"), EnumFoodTextures.FOOD.SAUSAGE.getIndex(), 2, 1.2f, "Sausage");
        return this.hooked;
    }

    @Override
    protected void recipes() {
    }
}
