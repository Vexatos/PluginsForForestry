package denoflionsx.PluginsforForestry.Plugins.Railcraft;

import cpw.mods.fml.common.Loader;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import denoflionsx.PluginsforForestry.Plugins.Railcraft.Items.ItemCustomCoke;
import denoflionsx.PluginsforForestry.Utils.FermenterUtils;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class PluginRailcraft implements IPfFPlugin, IdenWorldEventHandler {
    
    public static Item itemCharCoke;
    public static LiquidStack creosote;
    
    @Override
    public void onPreLoad() {
        if (Loader.isModLoaded("Railcraft")) {
        }
    }
    
    @Override
    public void onLoad() {
        if (Loader.isModLoaded("Railcraft")) {
            if (PfFTuning.getInt(PfFTuning.Railcraft.plugin_railcraft_charcoal) > 0) {
                itemCharCoke = new ItemCustomCoke(PfFTuning.getInt(PfFTuning.Railcraft.plugin_railcraft_charcoal));
                PfF.Proxy.ItemCollections.add(this.getClass());
            }
        }
    }
    
    @Override
    public void onPostLoad() {
        // Welcome to reflection hell.
        if (Loader.isModLoaded("Railcraft")) {
            creosote = LiquidDictionary.getLiquid("Creosote Oil", LiquidContainerRegistry.BUCKET_VOLUME);
            if (PfFTuning.getBool(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedSticks)) {
                ItemStack impSticks = Forestry.items("stickImpregnated");
                ItemStack pregCase = Forestry.items("impregnatedCasing");
                if (impSticks != null && pregCase != null) {
                    impSticks.stackSize = 2;
                    try {
                        Class c = Class.forName("forestry.factory.gadgets.MachineCarpenter");
                        Class RecipeManager = null;
                        Class Recipe = null;
                        Class Shaped = Class.forName("forestry.core.utils.ShapedRecipeCustom");
                        for (Class c2 : c.getDeclaredClasses()) {
                            if (Pattern.compile("$", Pattern.LITERAL).split(c2.getName())[1].equals("RecipeManager")) {
                                RecipeManager = c2;
                            } else if (Pattern.compile("$", Pattern.LITERAL).split(c2.getName())[1].equals("Recipe")) {
                                Recipe = c2;
                            }
                        }
                        if (RecipeManager != null && Recipe != null && Shaped != null) {
                            ArrayList<Object> list = (ArrayList<Object>) RecipeManager.getField("recipes").get(null);
                            ArrayList<Object> myList = new ArrayList();
                            LiquidStack oil = denLib.LiquidStackUtils.getNewStackCapacity(creosote, PfFTuning.getInt(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedSticks_Amount));
                            for (Object o : list) {
                                ItemStack i = (ItemStack) Recipe.getMethod("getCraftingResult", new Class[0]).invoke(o, new Object[0]);
                                if (i.isItemEqual(impSticks) || i.isItemEqual(pregCase)) {
                                    // Recipe found!
                                    Field temp = Recipe.getDeclaredField("internal");
                                    temp.setAccessible(true);
                                    Object internal = temp.get(o);
                                    temp = Recipe.getDeclaredField("packagingTime");
                                    temp.setAccessible(true);
                                    int time = temp.getInt(o);
                                    Object newRecipe = Recipe.getConstructor(new Class[]{int.class, LiquidStack.class, ItemStack.class, Shaped}).newInstance(time, oil, null, internal);
                                    myList.add(newRecipe);
                                }
                            }
                            list.addAll(myList);
                            RecipeManager.getField("recipes").set(null, list);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            WorldEventHandler.registerHandler(this);
        }
    }
    
    @Override
    public void onWorldLoaded() {
        LiquidStack copy = denLib.LiquidStackUtils.getNewStackCapacity(creosote, 1);
        FermenterUtils.registerFermenterBooster(copy, 1.5f);
        WorldEventHandler.unregisterHandler(this);
    }
    
    @Override
    public void onWorldEnded() {
    }
}
