package denoflionsx.plugins;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.API.API;
import denoflionsx.API.PFFItems;
import denoflionsx.core.ItemIDManager;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;
import denoflionsx.plugins.MinefactoryReloaded.MFRMilk;


public class pluginMineFactoryReloaded extends pluginBase {

    protected String theClass = "powercrystals.minefactoryreloaded.MineFactoryReloadedCore";
    public static MFRMilk milk;
    private ItemIDManager IDs = new ItemIDManager(2,"MFRMilk");

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

    }

    @Override
    protected boolean init() {
        if (!detect()){
            return this.hooked;
        }
        this.addItem(this.theClass, "milkItem", "Milk", 0);
        PFFItems.registerItem("milk",this.get("Milk").getItem());
        if (API.isPluginLoaded("BetterFarming")){
            this.addItem("Milk Bottle",pluginCore.plugins.get("BetterFarming").get("Milk Bottle"));
            milk = new MFRMilk(this.IDs,this.get("Milk"),this.get("Milk Bottle"),this.getOptionInt("MineFactory_MilkMJt"),this.getOptionInt("MineFactory_MilkBurnTime"),this.getOptionBool("MineFactory_MilkLosesHeat"));
        }else{
            ItemStack n = null;
            milk = new MFRMilk(this.IDs,this.get("Milk"),n,this.getOptionInt("MineFactory_MilkMJt"),this.getOptionInt("MineFactory_MilkBurnTime"),this.getOptionBool("MineFactory_MilkLosesHeat"));
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
//            ModLoader.addShapelessRecipe(API.getItem("milkbottle"), new Object[]{API.getItem("milkbottle_depricated")});
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
