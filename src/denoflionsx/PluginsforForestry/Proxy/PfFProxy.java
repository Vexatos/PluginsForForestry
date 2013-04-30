package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import net.minecraft.block.Block;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PfFProxy implements IPfFProxy {

    @Override
    public void print(String msg) {
        FMLLog.info("[" + PfFTranslator.instance.translateKey("console.pff.name") + "]" + ": " + msg);
    }

    @Override
    public void warning(String msg) {
        FMLLog.warning("[" + PfFTranslator.instance.translateKey("console.pff.name") + "]: " + msg);
    }

    @Override
    public void severe(String msg) {
        FMLLog.severe("[" + PfFTranslator.instance.translateKey("console.pff.name") + "]: " + msg);
    }

    @Override
    public void registerClientSide() {
    }

    @Override
    public void registerLiquidBlock(String perma, String name, LRLiquidBlock b) {
        String local = PfFTranslator.instance.translateKey(name);
        GameRegistry.registerBlock(b, local);
        LiquidStack stack = LiquidDictionary.getOrCreateLiquid(perma, new LiquidStack(b, 1000));
        LRBlocks.liquids.put(perma, b);
        LRLiquids.LRLiquids.put(perma, stack);
        LanguageRegistry.addName((Block) b, local);
    }

    @Override
    public void registerLiquidItem(String perma, String name, LRLiquidItem i) {
        String local = PfFTranslator.instance.translateKey(name);
        i.createItemEntry(0, local);
        LRItems.liquids.put(perma, i);
        LiquidStack stack = LiquidDictionary.getOrCreateLiquid(perma, new LiquidStack(i, 1000));
        LRLiquids.LRLiquids.put(perma, stack);
    }
}
