package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.API.API;
import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.Modules.milkModule;
import net.minecraft.server.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginMineFactoryReloaded extends pluginBase
{
    protected String theClass = "powercrystals.minefactoryreloaded.MineFactoryReloadedCore";
    public static multiItem milk;

    public pluginMineFactoryReloaded()
    {
        this.name = "pluginMineFactoryReloaded";
        this.mod = "mod_MineFactory";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();

            if (this.loaded = this.init())
            {
                this.recipes();
                milkModule.disableBCMilk = true;
            }
        }
    }

    protected void recipes()
    {
        try
        {
            byte var1;

            if (denLib.convertToBoolean(this.config.getOption("MineFactory_MilkLosesHeat")))
            {
                var1 = 2;
            }
            else
            {
                var1 = 1;
            }

            FuelManager.bronzeEngineFuel.put(Integer.valueOf(((ItemStack)this.items.get("Milk")).id), new EngineBronzeFuel((ItemStack)this.items.get("Milk"), Integer.valueOf(this.config.getOption("MineFactory_MilkMJt")).intValue(), Integer.valueOf(this.config.getOption("MineFactory_MilkBurnTime")).intValue(), var1));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Milk")).getItem(), 1000), ItemInterface.getItem("canEmpty"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Can")).intValue()));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Milk")).getItem(), 1000), ItemInterface.getItem("waxCapsule"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule")).intValue()));
            RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Milk")).getItem(), 1000), ItemInterface.getItem("refractoryEmpty"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule_Red")).intValue()));

            if (!((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
            {
                RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Milk")).getItem(), 1000), new ItemStack(Item.GLASS_BOTTLE, 1, 0), (ItemStack)this.items.get("Milk Bottle"));
            }
            else
            {
                RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Milk")).getItem(), 1000), new ItemStack(Item.GLASS_BOTTLE, 1, 0), ((pluginBase)pluginCore.plugins.get("BetterFarming")).get("Milk Bottle"));
            }
        }
        catch (Exception var2)
        {
            var2.printStackTrace();
        }
    }

    protected boolean init()
    {
        String var1 = "";

        if (this.detect())
        {
            try
            {
                this.addItem(this.theClass, "milkItem", "Milk", 0);
                milk = new multiItem(this.getOptionInt("MilkContainers_ItemID").intValue(), "mrfmilkcontainers");
                milk.metaMap.put("DO NOT USE", Integer.valueOf(0));
                milk.metaMap.put("Milk Capsule", Integer.valueOf(1));
                milk.metaMap.put("Milk Can", Integer.valueOf(2));
                milk.metaMap.put("Milk Capsule_Red", Integer.valueOf(3));
                milk.metaMap.put("Milk Bottle", Integer.valueOf(4));
                milk.metaMap.put("Milk Cell", Integer.valueOf(5));
                milk.add("milkcan", ((Integer)milk.metaMap.get("Milk Can")).intValue(), 17, "Milk Can");
                milk.add("milkcap", ((Integer)milk.metaMap.get("Milk Capsule")).intValue(), 33, "Milk Capsule");
                milk.add("milkcap_red", ((Integer)milk.metaMap.get("Milk Capsule_Red")).intValue(), 49, "Milk Capsule");

                if (((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
                {
                    LiquidContainerSystem.createWithOverride(milk, ((ItemStack)this.items.get("Milk")).id, ((pluginBase)pluginCore.plugins.get("BetterFarming")).get("Milk Bottle"), true);
                }
                else
                {
                    milk.add("milkbottle", ((Integer)milk.metaMap.get("Milk Bottle")).intValue(), 37, "Milk Bottle");
                    LiquidContainerSystem.createWithOverride(milk, ((ItemStack)this.items.get("Milk")).id, API.getItem("milkbottle"), true);
                }

                pluginCore.filled[1] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Capsule")).intValue());
                pluginCore.filled[2] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Can")).intValue());
                pluginCore.filled[3] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Capsule_Red")).intValue());
                this.depricated();

                if (((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
                {
                    LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(((ItemStack)this.items.get("Milk")).id, pluginCore.bottle), ((pluginBase)pluginCore.plugins.get("BetterFarming")).get("Milk Bottle"), new ItemStack(Item.GLASS_BOTTLE, 1, 0), false));
                }

                this.hooked = true;
            }
            catch (Exception var6)
            {
                var1 = "" + var6;
                this.hooked = false;
            }
            finally
            {
                if (var1.equals(""))
                {
                    var1 = this.getName() + " Loaded!";
                }
            }

            core.print(var1);
        }
        else
        {
            this.hooked = false;
        }

        return this.hooked;
    }

    public void depricated()
    {
        pluginCore.metaItem.add("milkcan_depricated", ((Integer)pluginCore.metaItem.metaMap.get("Milk Can")).intValue(), 17, "Milk Can (Obsolete)");
        pluginCore.metaItem.add("milkcap_depricated", ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule")).intValue(), 33, "Milk Capsule (Obsolete)");
        pluginCore.metaItem.add("milkcap_red_depricated", ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule_Red")).intValue(), 49, "Milk Capsule (Obsolete)");

        if (!((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
        {
            pluginCore.metaItem.add("milkbottle_depricated", ((Integer)pluginCore.metaItem.metaMap.get("Milk Bottle")).intValue(), 37, "Milk Bottle (Obsolete)");
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(((ItemStack)this.items.get("Milk")).id, pluginCore.bottle), new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Bottle")).intValue()), new ItemStack(Item.GLASS_BOTTLE, 1, 0), false));
            ModLoader.addShapelessRecipe(API.getItem("milkbottle"), new Object[] {API.getItem("milkbottle_depricated")});
        }

        ArrayList var1 = new ArrayList();
        var1.add(new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule")).intValue()));
        var1.add(new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Can")).intValue()));
        var1.add(new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule_Red")).intValue()));

        if (!((pluginBase)pluginCore.plugins.get("BetterFarming")).loaded)
        {
            var1.add(new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Milk Capsule")).intValue()));
        }

        Iterator var2 = var1.iterator();

        while (var2.hasNext())
        {
            ItemStack var3 = (ItemStack)var2.next();
            ModLoader.addShapelessRecipe(new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Capsule")).intValue()), new Object[] {var3});
        }
    }

    protected void defaults()
    {
        this.config.addDefault("[MineFactoryReloaded Options]");
        this.config.addDefault("MineFactory_MilkMJt=1");
        this.config.addDefault("MineFactory_MilkBurnTime=40000");
        this.config.addDefault("MilkContainers_ItemID=" + String.valueOf(core.ItemIDs[8]));
        this.config.addDefault("# If you think Milk is OP, change the next option to true");
        this.config.addDefault("MineFactory_MilkLosesHeat=false");
        this.config.writeConfig();
        this.config.readFile();
    }
}
