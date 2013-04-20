package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
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
}
