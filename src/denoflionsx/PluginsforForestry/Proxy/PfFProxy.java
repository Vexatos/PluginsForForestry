package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.API.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.BarrelRequirements.Items.BarrelItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PfFProxy implements IPfFProxy {

    @Override
    public void print(String msg) {
        FMLLog.info("[" + PfFTranslator.instance.translateKey("console.pff.name") + "]" + ": " + msg);
    }

    @Override
    public void registerRecipe(ItemStack i, Object[] o) {
        GameRegistry.addRecipe(i, o);
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
        LiquidStack stack = LiquidDictionary.getOrCreateLiquid(perma, new LiquidStack(i, LiquidContainerRegistry.BUCKET_VOLUME));
        LRLiquids.LRLiquids.put(perma, stack);
    }

    @Override
    public void registerAllRecipes() {
        try {
            PfF.Proxy.print("Registering recipes.");
            Class[] classes = new Class[]{LRItems.class, BarrelItems.class};
            for (Class c : classes) {
                for (Field f : c.getDeclaredFields()) {
                    Object o = f.get(null);
                    if (o == null){
                        continue;
                    }
                    if (o instanceof IRegisterRecipe) {
                        IRegisterRecipe r = (IRegisterRecipe) o;
                        r.registerRecipe();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendMessageToPlayer(String msg) {
    }
}
