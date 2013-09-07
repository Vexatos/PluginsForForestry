package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup;

import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Client.Render.RenderThis;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.BarrelRecipeManager;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemBarrel;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemContainerBase;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemHammer;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemRings;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemWoodenBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.WoodenBucketRecipeManager;
import denoflionsx.denLib.Mod.Handlers.DictionaryHandler;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import denoflionsx.denLib.Mod.denLibMod;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

public class PluginLR implements IPfFPlugin {

    public static HashMap<String, ArrayList<String>> blackLists = new HashMap();
    //-----
    @RenderThis
    public static Item woodenBucket;
    @RenderThis(renderFile = "barrel.txt")
    public static Item barrel;
    public static Item hammer;
    public static Item rings;
    public static WoodenBucketRecipeManager woodenBucketRecipes;
    public static BarrelRecipeManager barrelRecipes;
    public static HashMap<String, ItemStack> stacks = new HashMap();

    @Override
    public void onPreLoad() {
        PfF.Proxy.registerRenderable(this);
        PfFAPI.sendBlacklistRequest("woodenBucket", FluidRegistry.LAVA.getName());
        PfFAPI.sendBlacklistRequest("barrel", FluidRegistry.LAVA.getName());
    }

    @Override
    public void onLoad() {
        woodenBucket = new ItemWoodenBucket(PfFTuning.getInt(PfFTuning.Buckets.woodenbucket_ItemID), 1000, "item.pff.woodenbucket.name", "woodenBucket", "bucket_wood_birch");
        woodenBucketRecipes = new WoodenBucketRecipeManager();
        hammer = new ItemHammer(PfFTuning.getInt(PfFTuning.Items.hammer_ItemID));
        rings = new ItemRings(PfFTuning.getInt(PfFTuning.Items.rings_ItemID));
        barrel = new ItemBarrel(PfFTuning.getInt(PfFTuning.Items.barrel_ItemID), PfFTuning.getInt(PfFTuning.Barrel.barrel_capacity), "item.pff.barrel.name", "barrel", "barrel");
        barrelRecipes = new BarrelRecipeManager();
    }

    @Override
    public void onPostLoad() {
        try {
            Class c = this.getClass();
            for (Field f : c.getDeclaredFields()) {
                Object o = f.get(null);
                if (o != null) {
                    if (o instanceof ItemContainerBase) {
                        denLibMod.DictionaryHandler.registerListener((IDictionaryListener) o, DictionaryHandler.channels.FLUID);
                        WorldEventHandler.registerHandler((IdenWorldEventHandler) o);
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        PfF.Proxy.print("If anyone knows how to make my containers render properly in the player's hand please see me on Github. https://github.com/denoflionsx");
    }
}
