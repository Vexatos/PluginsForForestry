package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.Client.Render.RenderThis;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LRItems {

    public static HashMap<String, ItemMeta> liquids = new HashMap();
    //---------------------------------------------------------------------
    public static Item itemWoodenBucketEmpty = null;
    public static ItemStack ItemStackWoodenBucketEmpty = null;
    // Default RenderThis mode is bucket.
    @RenderThis
    public static HashMap<Integer, Item> customBucketsFilled = new HashMap();
    @RenderThis(renderFile = "barrel.txt")
    public static ItemContainer barrel;
    public static ItemStack barrelEmpty;
    @RenderThis(renderFile = "capsule.txt")
    public static ItemContainer capsule;
    @RenderThis(renderFile = "capsule.txt")
    public static ItemContainer rcapsule;
    //----------------------------------------------------------------------
    public static BiMap<Integer, ItemStack> bucketStacks = HashBiMap.create();
    public static BiMap<Integer, ItemStack> woodenBucketstacks = HashBiMap.create();
    //-----------------------------------------------------------------------
}
