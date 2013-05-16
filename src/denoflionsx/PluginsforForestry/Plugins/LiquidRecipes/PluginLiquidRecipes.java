package denoflionsx.PluginsforForestry.Plugins.LiquidRecipes;

import denoflionsx.PluginsforForestry.Dictionary.ModuleParser;
import denoflionsx.PluginsforForestry.Dictionary.PfFDictionaryParser;
import denoflionsx.PluginsforForestry.Dictionary.PfFReflectionParser;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Dictionary.ReflectionModuleParser;
import denoflionsx.PluginsforForestry.Dictionary.Vanilla;
import denoflionsx.PluginsforForestry.EventHandler.FermenterRecipes;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class PluginLiquidRecipes implements IPfFPlugin {

    @Override
    public void onPreLoad() {
        PfFDictionaryParser.createInstance();
        PfFReflectionParser.createInstance();
        FermenterRecipes.register();
    }

    @Override
    public void onLoad() {
        Vanilla.inject();
    }

    @Override
    public void onPostLoad() {
        // Setup squeezer recipes based on parsed lists.
        for (ArrayList<ModuleParser.SqueezeObject> s : PfFDictionaryParser.instance.getLists().values()) {
            for (ModuleParser.SqueezeObject s2 : s) {
                for (ItemStack i : s2.getItemStackFromDictionary()) {
                    LiquidStack l = s2.getLiquidStack(s2.getAmount());
                    if (l != null) {
                        Forestry.squeezer(5, new ItemStack[]{i}, l);
                    }
                }
            }
        }
        for (ArrayList<ReflectionModuleParser.ClassAndField> s : PfFReflectionParser.instance.map.values()) {
            for (ReflectionModuleParser.ClassAndField s2 : s) {
                LiquidStack l = s2.getSqueezeobject().getLiquidStack(s2.getSqueezeobject().getAmount());
                for (ItemStack i : PfFReflectionParser.instance.getEntriesAsItemStacksForType(s2.getSqueezeobject().getLiquidTag())) {
                    if (l != null) {
                        Forestry.squeezer(5, new ItemStack[]{i}, l);
                    }
                }
            }
        }

    }
}
