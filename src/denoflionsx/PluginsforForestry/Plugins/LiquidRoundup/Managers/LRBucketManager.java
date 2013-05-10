package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemContainer;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemLRBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemWoodenBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import java.io.File;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class LRBucketManager {

    protected BiMap<String, Integer> bucketIDMap = HashBiMap.create();
    protected File saveFile = new File(PfF.core.mappingsDir.getAbsolutePath() + "/DefaultBucketMappings.BiMap");
    //--------------------------------------------------------------
    protected ReflectionHelper r = null;
    protected BucketType bucket = null;

    public LRBucketManager(BucketType bucket) {
        if (saveFile.exists()) {
            bucketIDMap = denLib.FileUtils.readBiMapFromFile(saveFile);
        }
        r = new ReflectionHelper(bucket.bucketName, bucket.bucketParam);
        this.bucket = bucket;
    }

    public void processLiquid(LiquidDictionary.LiquidRegisterEvent event) {
        if (LiquidContainerRegistry.fillLiquidContainer(event.Liquid, bucket.empty) != null) {
            return;
        }
        if (event.Name.equals("Lava")) {
            if (this.bucket.madeOf.isDoesMelt()) {
                return;
            }
        }
        if (this.bucket.isBlacklisted(event)){
            PfF.Proxy.print("Liquid " + event.Name + " skipped iron bucket due to blacklist.");
            return;
        }
        String n = PfFLib.PffStringUtils.getItemName(event.Liquid.asItemStack());
        if (n.equals("")) {
            n = PfFLib.PffStringUtils.cleanLiquidNameFromEvent(event);
        }
        int id;
        String hash = PfFLib.PffStringUtils.Hash(String.valueOf(event.Liquid.itemID + "|" + event.Liquid.itemMeta));
        if (bucketIDMap.containsKey(hash)) {
            id = bucketIDMap.get(hash);
            PfF.Proxy.print("Found known default ID for bucket " + n + ": " + id);
        } else {
            id = PfFLib.MathUtils.getLastID(bucketIDMap.inverse());
            if (id == 1) {
                id += PfFTuning.bucketIDRangeStart;
            }
            bucketIDMap.put(hash, id);
            PfF.Proxy.print("New default id assigned for bucket " + n + ": " + id);
        }
        if (r.getBucketItemID(n, id) > 0) {
            LRItems.customBucketsFilled.put(r.getBucketItemID(n, id), this.bucket.createNewBucket(r.getBucketItemID(n, id), event.Liquid.itemID, n + " " + PfF.Proxy.translate("item.pff.bucket")));
            ItemStack i = r.getBucketItemStack(r.getBucketItemID(n, id));
            if (event.Liquid != null && i != null && bucket.empty != null) {
                LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(event.Liquid, LiquidContainerRegistry.BUCKET_VOLUME), i, bucket.empty));
            } else {
                if (event.Liquid == null) {
                    PfF.Proxy.severe("Liquid is null! Cancelling container!");
                } else if (i == null) {
                    PfF.Proxy.severe("Failed to create container!");
                } else if (bucket.empty == null) {
                    PfF.Proxy.severe("Bucket Enum error!");
                }
            }
            bucket.addToBucketList(event.Liquid, i);
        }
    }

    public void processLiquids() {
        for (LiquidDictionary.LiquidRegisterEvent event : PluginLR.events) {
            processLiquid(event);
        }
        if (saveFile.exists()) {
            saveFile.delete();
        }
        denLib.FileUtils.saveBiMapToFile(bucketIDMap, saveFile);
        PfFTuning.config.save();
    }

    public class ReflectionHelper {

        // This used to have some actual reflection in it.
        // but I redesigned this entire manager.
        protected String bucketName;
        private String bucketParam;

        public ReflectionHelper(String bucketName, String bucketParam) {
            this.bucketName = bucketName;
            this.bucketParam = bucketParam;
        }

        public int getBucketItemID(String name, int id) {
            String n = denLib.StringUtils.removeSpaces(name.toLowerCase()) + this.bucketParam;
            int i = PfFTuning.config.getItem("items.buckets", n, id).getInt();
            return i;
        }

        public ItemStack getBucketItemStack(Integer id) {
            return new ItemStack(LRItems.customBucketsFilled.get(id));
        }
    }

    public static enum BucketType {

        IRON("Bucket", "_IronBucket_ItemID", LiquidContainerRegistry.EMPTY_BUCKET, ItemContainer.MATERIAL.iron),
        WOODEN("WoodenBucket", "_WoodenBucket_ItemID", LRItems.ItemStackWoodenBucketEmpty, ItemContainer.MATERIAL.wood);
        public String bucketName;
        public String bucketParam;
        public ItemStack empty;
        public ItemContainer.MATERIAL madeOf;

        private BucketType(String bucketName, String bucketParam, ItemStack empty, ItemContainer.MATERIAL madeOf) {
            this.bucketName = bucketName;
            this.bucketParam = bucketParam;
            this.empty = empty;
            this.madeOf = madeOf;
        }

        public Item createNewBucket(int itemID, int liquidID, String name) {
            if (this.equals(IRON)) {
                return (Item) new ItemLRBucket(itemID, liquidID, name);
            } else {
                return (Item) new ItemWoodenBucket(itemID, liquidID, name);
            }
        }

        public void addToBucketList(LiquidStack l, ItemStack b) {
            if (this.equals(IRON)) {
                LRItems.bucketStacks.put(l.itemID, b);
            } else {
                LRItems.woodenBucketstacks.put(l.itemID, b);
            }
        }

        public boolean isBlacklisted(LiquidDictionary.LiquidRegisterEvent event) {
            if (this.equals(IRON)) {
                for (String s : Blacklists.ironBucket) {
                    if (s.equals(event.Name)) {
                        return true;
                    }
                }
            }else if (this.equals(WOODEN)){
                for (String s : Blacklists.woodenBucket){
                    if (s.equals(event.Name)){
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
