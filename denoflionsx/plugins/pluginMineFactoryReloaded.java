package denoflionsx.plugins;

import net.minecraft.src.ItemStack;
import denoflionsx.API.PfFAPI;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;
import denoflionsx.plugins.MinefactoryReloaded.MFRMilk;

public class pluginMineFactoryReloaded extends pluginBase {

    protected String theClass = EnumModIDs.MODS.MFR.gettheClass();
    public static MFRMilk milk;
    private ItemIDManager IDs = new ItemIDManager(2, "MFRMilk");

    public pluginMineFactoryReloaded() {
        this.name = "pluginMineFactoryReloaded";
        this.mod = EnumModIDs.MODS.MFR.getID();
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        if (!loaded) {
            if (this.detect()){
                milkModule.disableBCMilk = true;
            }
        }
        super.register();
    }

    @Override
    protected void recipes() {
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return this.hooked;
        }
        this.addItem(this.theClass, "milkItem", "Milk", 0);
        PfFManagers.ItemManager.registerItem("milk", this.get("Milk").getItem());
        if (PfFAPI.isPluginLoaded("BetterFarming")) {
            this.addItem("Milk Bottle", PluginRegistry.plugins.get("BetterFarming").get("Milk Bottle"));
            milk = new MFRMilk(this.IDs, this.get("Milk"), this.get("Milk Bottle"), this.getOptionInt("MineFactory_MilkMJt"), this.getOptionInt("MineFactory_MilkBurnTime"), this.getOptionBool("MineFactory_MilkLosesHeat"));
        } else {
            ItemStack n = null;
            milk = new MFRMilk(this.IDs, this.get("Milk"), n, this.getOptionInt("MineFactory_MilkMJt"), this.getOptionInt("MineFactory_MilkBurnTime"), this.getOptionBool("MineFactory_MilkLosesHeat"));
        }
        this.hooked = true;
        return this.hooked;
    }

//    public void depricated() {
//        // These containers in pluginCore are now depricated and will be removed in a later version.
//        pluginCore.metaItem.add("milkcan_depricated", pluginCore.metaItem.metaMap.get("Milk Can"), 17, "Milk Can (Obsolete)");
//        pluginCore.metaItem.add("milkcap_depricated", pluginCore.metaItem.metaMap.get("Milk Capsule"), 17 + 16, "Milk Capsule (Obsolete)");
//        pluginCore.metaItem.add("milkcap_red_depricated", pluginCore.metaItem.metaMap.get("Milk Capsule_Red"), 17 + 16 + 16, "Milk Capsule (Obsolete)");
//        if (!pluginCore.plugins.get("BetterFarming").loaded) {
//            pluginCore.metaItem.add("milkbottle_depricated", pluginCore.metaItem.metaMap.get("Milk Bottle"), 20 + 16 + 1, "Milk Bottle (Obsolete)");
//            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(this.items.get("Milk").itemID, pluginCore.bottle), new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Bottle")), new ItemStack(Item.glassBottle, 1, 0), false));
//            ModLoader.addShapelessRecipe(PfFManagers.ItemManager.getItem("milkbottle"), new Object[]{PfFManagers.ItemManager.getItem("milkbottle_depricated")});
//        }
//        ArrayList<ItemStack> i = new ArrayList();
//        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule")));
//        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Can")));
//        i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule_Red")));
//        if (!pluginCore.plugins.get("BetterFarming").loaded) {
//            i.add(new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Milk Capsule")));
//        }
//        for (ItemStack a : i){
//            ModLoader.addShapelessRecipe(new ItemStack(milk,1,milk.metaMap.get("Milk Capsule")), new Object[]{a});
//        }
//        
//    }
    @Override
    protected void defaults() {
        config.addDefault("[MineFactoryReloaded Options]");
        config.addDefault("MineFactory_MilkMJt=1");
        config.addDefault("MineFactory_MilkBurnTime=40000");
        config.addDefault("MilkContainers_ItemID=" + IDs.getItemIDs().get(0));
        config.addDefault("# If you think Milk is OP, change the next option to true");
        config.addDefault("MineFactory_MilkLosesHeat=false");
        config.writeConfig();
        config.readFile();

    }
}
