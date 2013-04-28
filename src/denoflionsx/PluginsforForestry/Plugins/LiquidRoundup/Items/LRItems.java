package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.denLib.Mod.Items.ItemMeta;
import java.util.HashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LRItems {

    public static HashMap<String, ItemMeta> liquids = new HashMap();
    public static HashMap<String, ItemMeta> containers = new HashMap();
    public static HashMap<ItemMeta, Integer> containerSize = new HashMap();
    //---------------------------------------------------------------------
    public static ItemLRBucket itemVeggieJuiceBucket = null;
    public static ItemLRBucket itemLiquidPeatBucket = null;
    //---------------------------------------------------------------------
    public static Item itemWoodenBucketEmpty = null;
    public static ItemStack ItemStackWoodenBucketEmpty = null;
    public static HashMap<String, Item> customBucketsFilled = new HashMap();
}
