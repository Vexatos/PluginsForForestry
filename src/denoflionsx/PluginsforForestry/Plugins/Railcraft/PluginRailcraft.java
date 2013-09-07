package denoflionsx.PluginsforForestry.Plugins.Railcraft;

import cpw.mods.fml.common.Loader;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import denoflionsx.PluginsforForestry.Plugins.Railcraft.Items.ItemCustomCoke;
import denoflionsx.PluginsforForestry.Utils.FermenterUtils;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.DictionaryHandler;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class PluginRailcraft implements IPfFPlugin, IdenWorldEventHandler, IDictionaryListener {

    public static Item itemCharCoke;
    public static FluidStack creosote;

    @Override
    public void onPreLoad() {
        if (Loader.isModLoaded("Railcraft")) {
            denLibMod.DictionaryHandler.registerListener(this, DictionaryHandler.channels.FLUID);
        }
    }

    @Override
    public void onEvent(String tag, short channel, Object o) {
        if (tag.equals("creosote")) {
            creosote = new FluidStack((Fluid) o, FluidContainerRegistry.BUCKET_VOLUME);
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
            if (creosote == null) {
                PfF.Proxy.severe("Creosote oil not found!");
            }
            if (PfFTuning.getBool(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedSticks)) {
                ItemStack impSticks = Forestry.items("stickImpregnated");
                if (impSticks != null) {
                    impSticks.stackSize = 2;
                    doCarpenter(impSticks, PfFTuning.getInt(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedSticks_Amount));
                }
            }

            if (PfFTuning.getBool(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedCasings)) {
                ItemStack pregCase = Forestry.items("impregnatedCasing");
                if (pregCase != null) {
                    doCarpenter(pregCase, PfFTuning.getInt(PfFTuning.Railcraft_.plugin_railcraft_CreosoteOilForImpregnatedCasings_Amount));
                }
            }

            WorldEventHandler.registerHandler(this);
        }
    }

    private void doCarpenter(ItemStack item, int liquidAmount) {
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
                FluidStack oil = denLib.LiquidStackUtils.getNewStackCapacity(creosote, liquidAmount);
                for (Object o : list) {
                    ItemStack i = (ItemStack) Recipe.getMethod("getCraftingResult", new Class[0]).invoke(o, new Object[0]);
                    if (i.isItemEqual(item)) {
                        // Recipe found!
                        Field temp = Recipe.getDeclaredField("internal");
                        temp.setAccessible(true);
                        Object internal = temp.get(o);
                        temp = Recipe.getDeclaredField("packagingTime");
                        temp.setAccessible(true);
                        int time = temp.getInt(o);
                        Object newRecipe = Recipe.getConstructor(new Class[]{int.class, FluidStack.class, ItemStack.class, Shaped}).newInstance(time, oil, null, internal);
                        myList.add(newRecipe);
                    }
                }
                list.addAll(myList);
                PfF.Proxy.print("Injected " + myList.size() + " recipes into Carpenter.");
                RecipeManager.getField("recipes").set(null, list);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onWorldLoaded() {
        FluidStack copy = denLib.LiquidStackUtils.getNewStackCapacity(creosote, 1);
        FermenterUtils.registerFermenterBooster(copy, 1.5f);
        WorldEventHandler.unregisterHandler(this);
    }

    @Override
    public void onWorldEnded() {
    }
}
