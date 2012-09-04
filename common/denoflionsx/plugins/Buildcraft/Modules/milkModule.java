package denoflionsx.plugins.Buildcraft.Modules;

import net.minecraft.src.ItemStack;
import denoflionsx.denLib.denLib;
import denoflionsx.items.multiItem;
import denoflionsx.plugins.Forestry.LiquidContainerSystem;
import denoflionsx.plugins.baseModule;
import denoflionsx.plugins.pluginBase;
import denoflionsx.plugins.pluginCore;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import denoflionsx.API.API;

public class milkModule extends baseModule {

    public static multiItem milk;
    public static boolean disableBCMilk = false;
    //public static ItemIDManager IDs = new ItemIDManager(2);

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
            milk.add("milk", milk.metaMap.get("Milk"), 1, "Milk");
            milk.add("milkcap", milk.metaMap.get("Milk Capsule"), 33, "Milk Capsule");
            milk.add("milkcan", milk.metaMap.get("Milk Can"), 1 + 16, "Milk Can");
            milk.add("milkcap_red", milk.metaMap.get("Milk Capsule_Red"), (1 + 16 + 16 + 16), "Milk Capsule");
            if (!API.isPluginLoaded("BetterFarming")) {
                milk.add("milkbottle", milk.metaMap.get("Milk Bottle"), 20 + 16 + 1, "Milk Bottle");
                LiquidContainerSystem.createWithOverride(milk,milk.shiftedIndex,API.getItem("mcmilkbottle"),true);
            } else {
                LiquidContainerSystem.createWithOverride(milk, milk.shiftedIndex, pluginCore.plugins.get("BetterFarming").get("Milk Bottle"), true);
            }
            LiquidContainerSystem.registerMilkBucket(milk.shiftedIndex);
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
