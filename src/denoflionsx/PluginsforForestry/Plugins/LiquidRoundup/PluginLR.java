package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.EventHandler.FermenterRecipes;
import denoflionsx.PluginsforForestry.EventHandler.FermenterRecipes.Fermentable;
import denoflionsx.PluginsforForestry.EventHandler.LiquidDebug;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Handlers.BucketEventRegistry;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Handlers.LRBucketEvent;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemContainer;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemWoodenBucketEmpty;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRBucketManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRContainerManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRLiquidManager;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;
import forestry.api.core.ItemInterface;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PluginLR implements IPfFPlugin {

    public static LRLiquidManager liquids = new LRLiquidManager();
    //---------------------------------------------------------------------
    public static LRBucketManager buckets;
    public static LRBucketManager woodenBuckets;
    public static LRContainerManager containers;
    //---------------------------------------------------------------------
    public static BucketEventRegistry bucketEvents = new BucketEventRegistry();
    public static ArrayList<LiquidDictionary.LiquidRegisterEvent> events = new ArrayList();

    @ForgeSubscribe
    public void onLiquidRegister(LiquidDictionary.LiquidRegisterEvent event) {
        events.add(event);
    }

    @Override
    public void onPreLoad() {
        for (LiquidStack l : LiquidDictionary.getLiquids().values()) {
            events.add(new LiquidDictionary.LiquidRegisterEvent(l.asItemStack().getDisplayName(), l));
        }
        denLibMod.Proxy.registerForgeSubscribe(this);
    }

    @Override
    public void onLoad() {
        if (PfFTuning.getInt(PfFTuning.Buckets.woodenbucket_ItemID) > 0) {
            LRItems.itemWoodenBucketEmpty = new ItemWoodenBucketEmpty(PfFTuning.getInt(PfFTuning.Buckets.woodenbucket_ItemID), 0, "item.pff.woodenbucket.name");
            LRItems.ItemStackWoodenBucketEmpty = new ItemStack(LRItems.itemWoodenBucketEmpty);
            woodenBuckets = new LRBucketManager(LRBucketManager.BucketType.WOODEN);
        }
        buckets = new LRBucketManager(LRBucketManager.BucketType.IRON);
        if (PfFTuning.getInt(PfFTuning.Items.barrel_ItemID) > 0) {
            LRItems.barrel = new ItemContainer(PfFTuning.getInt(PfFTuning.Items.barrel_ItemID), "PluginsforForestry:barrel", ItemContainer.MATERIAL.wood);
            LRItems.barrelEmpty = LRItems.barrel.createItemEntry(0, PfFTranslator.instance.translateKey("item.pff.barrel.name"));
        }
        if (PfFTuning.getInt(PfFTuning.Items.capsule_ItemID) > 0) {
            LRItems.capsule = new ItemContainer(PfFTuning.getInt(PfFTuning.Items.capsule_ItemID), "PluginsforForestry:capsule", ItemContainer.MATERIAL.wax);
        }
        if (PfFTuning.getInt(PfFTuning.Items.redcapsule_ItemID) > 0) {
            LRItems.rcapsule = new ItemContainer(PfFTuning.getInt(PfFTuning.Items.redcapsule_ItemID), "PluginsforForestry:rcapsule", ItemContainer.MATERIAL.refractory);
        }
        if (PfFTuning.getBool(PfFTuning.Liquids.liquid_asBlock)) {
            this.createLiquidBlockForm("Veggie Juice", "liquid.pff.veggiejuice.name", PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID));
            this.createLiquidBlockForm("Liquid Peat", "liquid.pff.liquidpeat.name", PfFTuning.getInt(PfFTuning.Blocks.liquidpeat_BlockID));
        } else {
            this.createLiquidItemForm("Veggie Juice", "liquid.pff.veggiejuice.name", PfFTuning.getInt(PfFTuning.Items.veggiejuice_ItemID));
            this.createLiquidItemForm("Liquid Peat", "liquid.pff.liquidpeat.name", PfFTuning.getInt(PfFTuning.Items.liquidpeat_ItemID));
        }
        this.registerAsFermentable(LRLiquids.LRLiquids.get("Veggie Juice"), PfFTuning.getFloat(PfFTuning.Liquids.veggiejuice_FermenterBonus));
        this.registerAsFermentable(LRLiquids.LRLiquids.get("Liquid Peat"), PfFTuning.getFloat(PfFTuning.Liquids.liquidpeat_FermenterBonus));
    }

    @Override
    public void onPostLoad() {
        for (LiquidDictionary.LiquidRegisterEvent e : events) {
            liquids.processLiquidFromDictionary(e);
        }
        buckets.processLiquids();
        if (woodenBuckets != null) {
            woodenBuckets.processLiquids();
        }
        bucketEvents.registerHandler(new LRBucketEvent());
        containers = new LRContainerManager();
        if (LRItems.barrel != null) {
            containers.registerContainer(LRItems.barrelEmpty, LRItems.barrel, PfFTranslator.instance.translateKey("item.pff.barrel.name"), PfFTuning.getInt(PfFTuning.Items.barrel_capacity));
        }
        if (LRItems.capsule != null) {
            containers.registerContainer(ItemInterface.getItem("waxCapsule"), LRItems.capsule, PfFTranslator.instance.translateKey("item.pff.capsule.name"), LiquidContainerRegistry.BUCKET_VOLUME);
        }
        if (LRItems.rcapsule != null) {
            containers.registerContainer(ItemInterface.getItem("refractoryEmpty"), LRItems.rcapsule, PfFTranslator.instance.translateKey("item.pff.capsule.name"), LiquidContainerRegistry.BUCKET_VOLUME);
        }
        WorldEventHandler.registerHandler(new LiquidDebug());
    }

    public void createLiquidBlockForm(String perma, String name, int blockID) {
        if (blockID > 0) {
            Block b = new LRLiquidBlock(blockID, perma, name, denLib.StringUtils.removeSpaces(perma.toLowerCase()) + ".still");
            PfF.Proxy.registerLiquidBlock(perma, name, (LRLiquidBlock) b);
        }
    }

    public void createLiquidItemForm(String perma, String name, int itemID) {
        if (itemID > 0) {
            LRLiquidItem liquid = new LRLiquidItem(new String[]{"PluginsforForestry:" + PfFLib.PffStringUtils.getTextureFromName(perma)}, itemID);
            PfF.Proxy.registerLiquidItem(perma, name, liquid);
        }
    }

    public void registerAsFermentable(LiquidStack l, float bonus) {
        if (l != null) {
            FermenterRecipes.fermentables.add(new Fermentable(l, bonus));
        }
    }

    @ForgeSubscribe
    public void onBucket(FillBucketEvent e) {
        bucketEvents.runEvent(e);
    }
}
