package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemLRBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemWoodenBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
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
    protected File saveFile = new File(PfF.core.mappingsDir.getAbsolutePath() + "/DefaultBucketMappings.bin");
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
        int id;
        String liquidName = event.Name;
        String hash = PfFLib.PffStringUtils.Hash(String.valueOf(event.Liquid.itemID + "|" + event.Liquid.itemMeta));
        if (bucketIDMap.containsKey(hash)) {
            id = bucketIDMap.get(hash);
            PfF.Proxy.print("Found known default ID for bucket " + liquidName + ": " + id);
        } else {
            id = PfFLib.MathUtils.getLastID(bucketIDMap.inverse());
            if (id == 1) {
                id += 4200;
            }
            bucketIDMap.put(hash, id);
            PfF.Proxy.print("New default id assigned for bucket " + liquidName + ": " + id);
        }
        if (r.getBucketItemID(liquidName, id) > 0) {
            String saveHash = PfFLib.PffStringUtils.Hash(liquidName + "|" + this.bucket.bucketParam);
            LRItems.customBucketsFilled.put(saveHash, this.bucket.createNewBucket(r.getBucketItemID(liquidName, id), event.Liquid.itemID, liquidName + " " + PfFTranslator.instance.translateKey("item.pff.bucket")));
            ItemStack i = r.getBucketItemStack(saveHash);
            if (event.Liquid != null && i != null && bucket.empty != null) {
                LiquidContainerRegistry.registerLiquid(new LiquidContainerData(event.Liquid, i, bucket.empty));
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
            return PfFTuning.config.getItem("items.buckets", n, id).getInt();
        }

        public ItemStack getBucketItemStack(String name) {
            return new ItemStack(LRItems.customBucketsFilled.get(name));
        }
    }

    public static enum BucketType {

        IRON("Bucket", "_IronBucket_ItemID", LiquidContainerRegistry.EMPTY_BUCKET, new File(PfF.core.mappingsDir.getAbsolutePath() + "/" + "BucketMappings.bin")),
        WOODEN("WoodenBucket", "_WoodenBucket_ItemID", LRItems.ItemStackWoodenBucketEmpty, LRContainerManager.c);
        public String bucketName;
        public String bucketParam;
        public ItemStack empty;
        public File saveFile;

        private BucketType(String bucketName, String bucketParam, ItemStack empty, File saveFile) {
            this.bucketName = bucketName;
            this.bucketParam = bucketParam;
            this.empty = empty;
            this.saveFile = saveFile;
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
                LRLiquids.bucketStacks.put(l.toString(), b);
            } else {
                LRLiquids.woodenBucketstacks.put(l.toString(), b);
            }
        }
    }
}
