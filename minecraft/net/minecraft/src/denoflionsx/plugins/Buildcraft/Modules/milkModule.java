package net.minecraft.src.denoflionsx.plugins.Buildcraft.Modules;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC2.addLiquidBC2;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC3.addLiquidBC3;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import net.minecraft.src.denoflionsx.plugins.pluginCore;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;

public class milkModule extends baseModule {

    public static multiItem milk;
    public static boolean disableBCMilk = false;

    public milkModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void init() {
        if (denLib.convertToBoolean(this.parent.config.getOption("MilkInTanks")) && !disableBCMilk) {
            milk = new multiItem(Integer.valueOf(this.parent.config.getOption("Milk_ItemID")), "liquidmilk");
            milk.metaMap.put("Milk", 0);
            milk.metaMap.put("Milk Capsule", 1);
            milk.metaMap.put("Milk Can", 2);
            milk.metaMap.put("Milk Capsule_Red", 3);
            milk.metaMap.put("Milk Bottle", 4);
            milk.metaMap.put("Milk Cell", 5);
            milk.add("bcliquidmilk", milk.metaMap.get("Milk"), 1, "Milk");
            milk.add("bcmilkcap", milk.metaMap.get("Milk Capsule"), 33, "Milk Capsule", 64);
            milk.add("bcmilkcan", milk.metaMap.get("Milk Can"), 1 + 16, "Milk Can", 64);
            milk.add("bcmilkcap_red", milk.metaMap.get("Milk Capsule_Red"), (1 + 16 + 16 + 16), "Milk Capsule", 64);
            if (!pluginCore.plugins.get("BetterFarming").loaded) {
                milk.add("bcmilkbottle", milk.metaMap.get("Milk Bottle"), 20 + 16 + 1, "Milk Bottle", 64);
                LiquidContainerSystem.create(milk);
            } else {
                LiquidContainerSystem.createWithOverride(milk, milk.shiftedIndex, pluginCore.plugins.get("BetterFarming").get("Milk Bottle"), true);
            }
            LiquidContainerSystem.registerMilkBucket(milk.shiftedIndex);
            pluginCore.filled[1] = new ItemStack(milk,1,milk.metaMap.get("Milk Capsule"));
            pluginCore.filled[2] = new ItemStack(milk,1,milk.metaMap.get("Milk Can"));
            pluginCore.filled[3] = new ItemStack(milk,1,milk.metaMap.get("Milk Capsule_Red"));
            recipes();
        }
    }

    @Override
    protected void recipes() {
        if (denLib.convertToBoolean(this.parent.config.getOption("MilkInBiogas")) && !disableBCMilk) {
            int b;
            if (denLib.convertToBoolean(this.parent.config.getOption("MilkLosesHeat"))) {
                b = 2;
            } else {
                b = 1;
            }
            FuelManager.bronzeEngineFuel.put(milk.shiftedIndex, new EngineBronzeFuel(new ItemStack(milk), Integer.valueOf(this.parent.config.getOption("MilkMJt")), Integer.valueOf(this.parent.config.getOption("MilkBurnTime")), b));
        }
    }

    @Override
    protected void defaults() {
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

    public static void load(pluginBase parent) {
        baseModule b = new milkModule(parent);
        b.register();
    }
}
