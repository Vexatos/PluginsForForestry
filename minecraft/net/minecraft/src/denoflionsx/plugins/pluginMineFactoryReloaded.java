package net.minecraft.src.denoflionsx.plugins;

import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.Modules.milkModule;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;


public class pluginMineFactoryReloaded extends pluginBase {

    protected String theClass = "powercrystals.minefactoryreloaded.MineFactoryReloadedCore";
    public static multiItem milk;

    public pluginMineFactoryReloaded() {
        this.name = "pluginMineFactoryReloaded";
        this.mod = "mod_MineFactory";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!loaded) {
            defaults();
            if (loaded = init()) {
                recipes();
                // Disable Buildcraft milk system if MFR is on.
                milkModule.disableBCMilk = true;
            }
        }
    }

    @Override
    protected void recipes() {

        try {
            int b;
            if (denLib.convertToBoolean(config.getOption("MineFactory_MilkLosesHeat"))) {
                b = 2;
            } else {
                b = 1;
            }
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(this.items.get("Milk").itemID), new EngineBronzeFuel(this.items.get("Milk"), Integer.valueOf(config.getOption("MineFactory_MilkMJt")), Integer.valueOf(config.getOption("MineFactory_MilkBurnTime")), b));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Milk").getItem(), 1000), ItemInterface.getItem("canEmpty"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Milk Can")));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Milk").getItem(), 1000), ItemInterface.getItem("waxCapsule"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Milk Capsule")));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Milk").getItem(), 1000), ItemInterface.getItem("refractoryEmpty"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Milk Capsule_Red")));
            if (!API.isPluginLoaded("BetterFarming")) {
                RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Milk").getItem(), 1000), new ItemStack(Item.glassBottle, 1, 0), this.items.get("Milk Bottle"));
            } else {
                RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Milk").getItem(), 1000), new ItemStack(Item.glassBottle, 1, 0), pluginCore.plugins.get("BetterFarming").get("Milk Bottle"));
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    @Override
    protected boolean init() {
        String msg = "";
        if (detect()) {
            try {
                this.addItem(this.theClass, "milkItem", "Milk", 0);
                milk = new multiItem(this.getOptionInt("MilkContainers_ItemID"), "mrfmilkcontainers");
                milk.metaMap.put("DO NOT USE", 0);
                milk.metaMap.put("Milk Capsule", 1);
                milk.metaMap.put("Milk Can", 2);
                milk.metaMap.put("Milk Capsule_Red", 3);
                milk.metaMap.put("Milk Bottle", 4);
                milk.metaMap.put("Milk Cell", 5);
                milk.add("milkcan", milk.metaMap.get("Milk Can"), 17, "Milk Can");
                milk.add("milkcap", milk.metaMap.get("Milk Capsule"), 17 + 16, "Milk Capsule");
                milk.add("milkcap_red", milk.metaMap.get("Milk Capsule_Red"), 17 + (16 * 2), "Milk Capsule");
                if (pluginCore.plugins.get("BetterFarming").loaded) {
                    LiquidContainerSystem.createWithOverride(milk, this.items.get("Milk").itemID, pluginCore.plugins.get("BetterFarming").get("Milk Bottle"), true);
                } else {
                    milk.add("milkbottle", milk.metaMap.get("Milk Bottle"), 20 + 16 + 1, "Milk Bottle");
                    LiquidContainerSystem.createWithOverride(milk, this.items.get("Milk").itemID, API.getItem("milkbottle"), true);
                }
                pluginCore.filled[1] = new ItemStack(milk, 1, milk.metaMap.get("Milk Capsule"));
                pluginCore.filled[2] = new ItemStack(milk, 1, milk.metaMap.get("Milk Can"));
                pluginCore.filled[3] = new ItemStack(milk, 1, milk.metaMap.get("Milk Capsule_Red"));
                //----------------
                this.depricated();
                //----------------
                if (pluginCore.plugins.get("BetterFarming").loaded) {
                    LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(this.items.get("Milk").itemID, pluginCore.bottle), pluginCore.plugins.get("BetterFarming").get("Milk Bottle"), new ItemStack(Item.glassBottle, 1, 0), false));
                }
                this.hooked = true;
            } catch (Exception ex) {
                msg = "" + ex;
                this.hooked = false;
            } finally {
                if (msg.equals("")) {
                    msg = getName() + " Loaded!";
                }
            }
            core.print(msg);
        } else {
            this.hooked = false;
        }
        return this.hooked;
    }

    public void depricated() {
        // These containers in pluginCore are now depricated and will be removed in a later version.
        pluginCore.metaItem.add("milkcan_depricated", pluginCore.metaItem.metaMap.get("Milk Can"), 17, "Milk Can (Obsolete)");
        pluginCore.metaItem.add("milkcap_depricated", pluginCore.metaItem.metaMap.get("Milk Capsule"), 17 + 16, "Milk Capsule (Obsolete)");
        pluginCore.metaItem.add("milkcap_red_depricated", pluginCore.metaItem.metaMap.get("Milk Capsule_Red"), 17 + 16 + 16, "Milk Capsule (Obsolete)");
        if (!pluginCore.plugins.get("BetterFarming").loaded) {
            pluginCore.metaItem.add("milkbottle_depricated", pluginCore.metaItem.metaMap.get("Milk Bottle"), 20 + 16 + 1, "Milk Bottle (Obsolete)");
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(this.items.get("Milk").itemID, pluginCore.bottle), new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Bottle")), new ItemStack(Item.glassBottle, 1, 0), false));
            ModLoader.addShapelessRecipe(API.getItem("milkbottle"), new Object[]{API.getItem("milkbottle_depricated")});
        }
        ArrayList<ItemStack> i = new ArrayList();
        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule")));
        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Can")));
        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule_Red")));
        if (!pluginCore.plugins.get("BetterFarming").loaded) {
            i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule")));
        }
        for (ItemStack a : i){
            ModLoader.addShapelessRecipe(new ItemStack(milk,1,milk.metaMap.get("Milk Capsule")), new Object[]{a});
        }
        
    }

    @Override
    protected void defaults() {
        config.addDefault("[MineFactoryReloaded Options]");
        config.addDefault("MineFactory_MilkMJt=1");
        config.addDefault("MineFactory_MilkBurnTime=40000");
        config.addDefault("MilkContainers_ItemID=" + String.valueOf(core.ItemIDs[8]));
        config.addDefault("# If you think Milk is OP, change the next option to true");
        config.addDefault("MineFactory_MilkLosesHeat=false");
        config.writeConfig();
        config.readFile();

    }
}
