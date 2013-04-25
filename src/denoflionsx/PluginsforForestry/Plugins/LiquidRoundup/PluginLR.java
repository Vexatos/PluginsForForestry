package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.EventHandler.FermenterRecipes;
import denoflionsx.PluginsforForestry.EventHandler.FermenterRecipes.Fermentable;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRBucketManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRContainerManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRLiquidManager;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import denoflionsx.denLib.Mod.denLibMod;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PluginLR implements IPfFPlugin {

    public static LRLiquidManager liquids = new LRLiquidManager();
    public static LRContainerManager containers = new LRContainerManager();
    public static LRBucketManager buckets = new LRBucketManager();
    private ArrayList<LiquidDictionary.LiquidRegisterEvent> events = new ArrayList();

    @Override
    public void onPreLoad() {
        denLibMod.Proxy.registerForgeSubscribe(this);
    }

    @Override
    public void onLoad() {
        if (PfFTuning.getInt(PfFTuning.Items.ironBucket_ItemID) > 0){
            // Set up bucket.
            LRItems.ironBucket = new ItemMeta(new String[]{PfF.core.containergfxpath + "bucket"}, PfFTuning.getInt(PfFTuning.Items.ironBucket_ItemID));
            LRItems.ironBucket.setUnlocalizedName("pff.ironbucket");
        }
        if (PfFTuning.getBool(PfFTuning.Liquids.liquid_asBlock)) {
            this.createLiquidBlockForm("Veggie Juice", PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID));
            this.createLiquidBlockForm("Liquid Peat", PfFTuning.getInt(PfFTuning.Blocks.liquidpeat_BlockID));
        } else {
            this.createLiquidItemForm("Veggie Juice", PfFTuning.getInt(PfFTuning.Items.veggiejuice_ItemID));
            this.createLiquidItemForm("Liquid Peat", PfFTuning.getInt(PfFTuning.Items.liquidpeat_ItemID));
        }
        this.registerAsFermentable(LRLiquids.LRLiquids.get("Veggie Juice"), PfFTuning.getFloat(PfFTuning.Liquids.veggiejuice_FermenterBonus));
        this.registerAsFermentable(LRLiquids.LRLiquids.get("Liquid Peat"), PfFTuning.getFloat(PfFTuning.Liquids.liquidpeat_FermenterBonus));
    }

    @Override
    public void onPostLoad() {
        for (LiquidDictionary.LiquidRegisterEvent e : events) {
            liquids.processLiquidFromDictionary(e);
            denLib.NBTUtils.saveObjectToNBTFile(containers.c, containers.liquidMap);
        }
        buckets.processLiquids();
    }

    @ForgeSubscribe
    public void onLiquidRegister(LiquidDictionary.LiquidRegisterEvent event) {
        events.add(event);
    }

    public void createLiquidBlockForm(String name, int blockID) {
        if (blockID > 0) {
            Block b = new LRLiquidBlock(blockID, name);
            PfF.Proxy.registerLiquidBlock(name, (LRLiquidBlock) b);
        }
    }

    public void createLiquidItemForm(String name, int itemID) {
        if (itemID > 0) {
            LRLiquidItem liquid = new LRLiquidItem(new String[]{PfF.core.liquidgfxpath + PfFLib.PffStringUtils.getTextureFromName(name)}, itemID);
            PfF.Proxy.registerLiquidItem(name, liquid);
        }
    }

    public void registerAsFermentable(LiquidStack l, float bonus) {
        if (l != null) {
            FermenterRecipes.fermentables.add(new Fermentable(l, bonus));
        }
    }

    @ForgeSubscribe
    public void onBucket(FillBucketEvent e) {
        for (LiquidStack l : LRLiquids.LRLiquids.values()) {
            if (l.itemID == e.world.getBlockId(e.target.blockX, e.target.blockY, e.target.blockZ)) {
                e.result = LRLiquids.bucketStacks.get(l.toString()).copy();
                e.world.setBlockToAir(e.target.blockX, e.target.blockY, e.target.blockZ);
                e.setResult(Event.Result.ALLOW);
            }
        }
    }
}
