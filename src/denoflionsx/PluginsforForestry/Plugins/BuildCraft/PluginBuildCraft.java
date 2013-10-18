package denoflionsx.PluginsforForestry.Plugins.BuildCraft;

import cpw.mods.fml.common.Loader;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.DictionaryHandler;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.denLibMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PluginBuildCraft implements IPfFPlugin, IDictionaryListener {

    private ItemStack woodenGear;

    @Override
    public void onPreLoad() {
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
        if (!Loader.isModLoaded("BuildCraft|Core")) {
            return;
        }
        try {
            Item gear = (Item) denLib.ReflectionHelper.getStaticField("buildcraft.BuildCraftCore", "woodenGearItem").get(null);
            woodenGear = new ItemStack(gear);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        PfF.Proxy.print("Injecting ore dict recipes for BC wooden gear...");
        denLibMod.DictionaryHandler.registerListener(this, DictionaryHandler.channels.ORE);
    }

    @Override
    public void onEvent(String tag, short channel, Object o) {
        if (tag.equals("stickWood")) {
            PfF.Proxy.registerRecipe(woodenGear, new Object[]{"XSX", "SXS", "XSX", Character.valueOf('S'), (ItemStack) o});
        }
    }
}
