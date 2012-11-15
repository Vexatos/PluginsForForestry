package denoflionsx.plugins.MinefactoryReloaded;

import net.minecraft.src.ItemStack;
import denoflionsx.API.PfFManagers;
import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.ItemIDManager;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.plugins.Buildcraft.Modules.milkModule;
import denoflionsx.plugins.MinefactoryReloaded.Items.MFRMilk;
import net.minecraftforge.liquids.LiquidDictionary;

public class pluginMineFactoryReloaded extends PfFPluginTemplate {

    protected String theClass = EnumModIDs.MODS.MFR.gettheClass();
    public static MFRMilk milk;
    private ItemIDManager IDs = new ItemIDManager(2, "MFRMilk");
    public static ItemStack tehMilk;

    public pluginMineFactoryReloaded(String name, String parent) {
        super(name, parent);
    }


    @Override
    public void recipes() {
    }

    @Override
    public void doSetup() {
        tehMilk = LiquidDictionary.getLiquid("milk", 1000).asItemStack();
        PfFManagers.ItemManager.registerItem("milk", tehMilk.getItem());
        ItemStack n = null;
        milk = new MFRMilk(this.IDs, tehMilk, n, this.config.getOptionInt("MineFactory_MilkMJt"), this.config.getOptionInt("MineFactory_MilkBurnTime"), this.config.getOptionBool("MineFactory_MilkLosesHeat"));
        milkModule.disableBCMilk = true;
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
    public void defaults() {
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
