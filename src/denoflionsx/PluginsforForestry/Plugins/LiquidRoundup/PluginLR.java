package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Interface.IPfFPlugin;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRContainerManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRLiquidManager;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.denLibMod;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidDictionary;

public class PluginLR implements IPfFPlugin {

    public static LRLiquidManager liquids = new LRLiquidManager();
    public static LRContainerManager containers = new LRContainerManager();
    private ArrayList<LiquidDictionary.LiquidRegisterEvent> events = new ArrayList();

    @Override
    public void onPreLoad() {
        denLibMod.Proxy.registerForgeSubscribe(this);
    }

    @Override
    public void onLoad() {
        if (PfFTuning.getBool(PfFTuning.Liquids.liquid_asBlock)) {
            this.createLiquidBlockForm("Veggie Juice", PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID));
            this.createLiquidBlockForm("Liquid Peat", PfFTuning.getInt(PfFTuning.Blocks.liquidpeat_BlockID));
        } else {
            this.createLiquidItemForm("Veggie Juice", PfFTuning.getInt(PfFTuning.Items.veggiejuice_ItemID));
            this.createLiquidItemForm("Liquid Peat", PfFTuning.getInt(PfFTuning.Items.liquidpeat_ItemID));
        }
    }

    @Override
    public void onPostLoad() {
        for (LiquidDictionary.LiquidRegisterEvent e : events) {
            liquids.processLiquidFromDictionary(e);
            denLib.NBTUtils.saveObjectToNBTFile(containers.c, containers.liquidMap);
        }
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
}
