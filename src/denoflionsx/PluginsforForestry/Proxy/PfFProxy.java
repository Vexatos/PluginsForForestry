package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.denLib.Lib.denLib;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PfFProxy implements IPfFProxy {

    @Override
    public void print(String msg) {
        FMLLog.info("[" + PfFTranslator.instance.translateKey("console.pff.name") + "]" + ": " + msg);
    }

    @Override
    public void registerClientSide() {
    }

    @Override
    public void registerLiquidBlock(String name, LRLiquidBlock b) {
        GameRegistry.registerBlock(b, b.getUnlocalizedName());
        LiquidStack stack = LiquidDictionary.getOrCreateLiquid(name, new LiquidStack(b, 1000));
        LRBlocks.liquids.put(name, b);
        LRLiquids.LRLiquids.put(name, stack);
    }

    @Override
    public void registerLiquidItem(String name, LRLiquidItem i) {
        i.createItemEntry(0);
        LRItems.liquids.put(name, i);
        i.setUnlocalizedName("pff." + denLib.StringUtils.removeSpaces(name.toLowerCase()));
        LiquidStack stack = LiquidDictionary.getOrCreateLiquid(name, new LiquidStack(i, 1000));
        LRLiquids.LRLiquids.put(name, stack);
    }
}
