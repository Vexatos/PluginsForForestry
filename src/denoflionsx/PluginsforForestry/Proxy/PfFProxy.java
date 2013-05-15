package denoflionsx.PluginsforForestry.Proxy;

import com.google.common.collect.BiMap;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.Wiki.Items.WikiBook;
import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRBlocks;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Tab.PfFTab;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
        try {
            for (Class c : ItemCollections) {
                for (Field f : c.getDeclaredFields()) {
                    Object o = f.get(null);
                    if (o instanceof Item) {
                        Item i = (Item) o;
                        i.setCreativeTab(PfFAPI.tab);
                    } else if (o instanceof ItemStack) {
                        ItemStack i = (ItemStack) o;
                        i.getItem().setCreativeTab(PfFAPI.tab);
                    } else if (o instanceof HashMap) {
                        HashMap<Integer, Item> map = (HashMap) o;
                        for (Item i : map.values()) {
                            i.setCreativeTab(PfFAPI.tab);
                        }
                    } else if (o instanceof BiMap) {
                        BiMap<Integer, ItemStack> map = (BiMap) o;
                        for (ItemStack i : map.values()) {
                            i.getItem().setCreativeTab(PfFAPI.tab);
                        }
                    } else if (o instanceof WikiBook) {
                        WikiBook i = (WikiBook) o;

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
