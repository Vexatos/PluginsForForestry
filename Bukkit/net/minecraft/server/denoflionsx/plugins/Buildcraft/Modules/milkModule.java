package net.minecraft.server.denoflionsx.plugins.Buildcraft.Modules;

import net.minecraft.server.denoflionsx.API.API;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.pluginCore;
import net.minecraft.server.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.server.ItemStack;

public class milkModule extends baseModule
{
    public static multiItem milk;
    public static boolean disableBCMilk = false;

    public milkModule(pluginBase var1)
    {
        super(var1);
    }

    protected void init()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("MilkInTanks")) && !disableBCMilk)
        {
            milk = new multiItem(Integer.valueOf(this.parent.config.getOption("Milk_ItemID")).intValue(), "liquidmilk");
            milk.metaMap.put("Milk", Integer.valueOf(0));
            milk.metaMap.put("Milk Capsule", Integer.valueOf(1));
            milk.metaMap.put("Milk Can", Integer.valueOf(2));
            milk.metaMap.put("Milk Capsule_Red", Integer.valueOf(3));
            milk.metaMap.put("Milk Bottle", Integer.valueOf(4));
            milk.metaMap.put("Milk Cell", Integer.valueOf(5));
            milk.add("bcliquidmilk", ((Integer)milk.metaMap.get("Milk")).intValue(), 1, "Milk");
            milk.add("bcmilkcap", ((Integer)milk.metaMap.get("Milk Capsule")).intValue(), 33, "Milk Capsule", 64);
            milk.add("bcmilkcan", ((Integer)milk.metaMap.get("Milk Can")).intValue(), 17, "Milk Can", 64);
            milk.add("bcmilkcap_red", ((Integer)milk.metaMap.get("Milk Capsule_Red")).intValue(), 49, "Milk Capsule", 64);

            if (!API.isPluginLoaded("BetterFarming"))
            {
                milk.add("bcmilkbottle", ((Integer)milk.metaMap.get("Milk Bottle")).intValue(), 37, "Milk Bottle", 64);
                LiquidContainerSystem.create(milk);
            }
            else
            {
                LiquidContainerSystem.createWithOverride(milk, milk.id, ((pluginBase)pluginCore.plugins.get("BetterFarming")).get("Milk Bottle"), true);
            }

            LiquidContainerSystem.registerMilkBucket(milk.id);
            pluginCore.filled[1] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Capsule")).intValue());
            pluginCore.filled[2] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Can")).intValue());
            pluginCore.filled[3] = new ItemStack(milk, 1, ((Integer)milk.metaMap.get("Milk Capsule_Red")).intValue());
            this.recipes();
        }
    }

    protected void recipes()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("MilkInBiogas")) && !disableBCMilk)
        {
            byte var1;

            if (denLib.convertToBoolean(this.parent.config.getOption("MilkLosesHeat")))
            {
                var1 = 2;
            }
            else
            {
                var1 = 1;
            }

            FuelManager.bronzeEngineFuel.put(Integer.valueOf(milk.id), new EngineBronzeFuel(new ItemStack(milk), Integer.valueOf(this.parent.config.getOption("MilkMJt")).intValue(), Integer.valueOf(this.parent.config.getOption("MilkBurnTime")).intValue(), var1));
        }
    }

    protected void defaults()
    {
        this.parent.config.addDefault("# These options are for an alternative to Minefactory for tankable milk.");
        this.parent.config.addDefault("# These options do nothing if the MFR plugin is on");
        this.parent.config.addDefault("MilkInTanks=true");
        this.parent.config.addDefault("Milk_ItemID=5314");
        this.parent.config.addDefault("MilkInBiogas=true");
        this.parent.config.addDefault("MilkMJt=1");
        this.parent.config.addDefault("MilkBurnTime=40000");
        this.parent.config.addDefault("# If you think Milk is OP, change the next option to true");
        this.parent.config.addDefault("MilkLosesHeat=false");
    }

    public static void load(pluginBase var0)
    {
        milkModule var1 = new milkModule(var0);
        var1.register();
    }
}
