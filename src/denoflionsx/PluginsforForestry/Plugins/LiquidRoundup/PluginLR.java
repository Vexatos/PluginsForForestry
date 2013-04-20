package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup;

import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Interface.IPfFPlugin;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRContainerManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers.LRLiquidManager;
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
        if (PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID) > 0) {
            Block veggiejuice = new LRLiquidBlock(PfFTuning.getInt(PfFTuning.Blocks.veggiejuice_BlockID), "Veggie Juice");
            PfF.Proxy.registerLiquidBlock("Veggie Juice", (LRLiquidBlock) veggiejuice);
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
}
