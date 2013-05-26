package denoflionsx.PluginsforForestry.Proxy;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Tab.PfFTab;
import denoflionsx.denLib.Lib.denLib;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PfFProxy implements IPfFProxy {

    public ArrayList<Class> ItemCollections = new ArrayList();

    @Override
    public void print(String msg) {
        FMLLog.info("[PfF]" + ": " + msg);
    }

    @Override
    public void registerRecipe(ItemStack i, Object[] o) {
        GameRegistry.addRecipe(i, o);
    }

    @Override
    public void warning(String msg) {
        FMLLog.warning("[PfF]: " + msg);
    }

    @Override
    public void severe(String msg) {
        FMLLog.severe("[PfF]: " + msg);
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
            for (Class c : ItemCollections) {
                for (Field f : c.getDeclaredFields()) {
                    f.setAccessible(true);
                    Object o = f.get(null);
                    if (o == null) {
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

    @Override
    public String translate(String key) {
        return PfFTranslator.instance.translateKey(key);
    }

    @Override
    public String getLang() {
        return "en_US";
    }

    @Override
    public void setTabs() {
        PfFAPI.tab = new PfFTab();
    }

    @Override
    public void findInternalAddons(File source) {
        this.print("Loading plugins...");
        ArrayList<Object> plugins = denLib.FileUtils.getClassesInJar(source, IPfFPlugin.class);
        for (Object o : plugins) {
            PfFAPI.plugins.registerPlugin((IPfFPlugin) o);
        }
        this.print("Done. " + plugins.size() + " plugins loaded.");
    }

    @Override
    public void registerShapelessRecipe(ItemStack i, ItemStack[] stacks) {
        GameRegistry.addShapelessRecipe(i, (Object[]) stacks);
    }
}
