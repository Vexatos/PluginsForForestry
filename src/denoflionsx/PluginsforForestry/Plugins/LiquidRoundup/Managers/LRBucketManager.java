package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemLRBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class LRBucketManager {

    private BiMap<Integer, String> bucketMap = HashBiMap.create();
    private File bucketMapSave = new File(PfF.core.configDir.getAbsolutePath() + "/BucketMap.bin");

    public LRBucketManager() {
        if (bucketMapSave.exists()) {
            bucketMap = (BiMap<Integer, String>) denLib.NBTUtils.restoreObjectFromNBTFile(bucketMapSave);
        }
    }

    public void processLiquid(LiquidStack l) {
        int f = PfFLib.MathUtils.getLastID(bucketMap);
        String liquidName = LRLiquids.getLiquidNameFromLiquidStack(l);
        String hash = PfFLib.PffStringUtils.Hash(String.valueOf(l.itemID + "|" + l.itemMeta));
        if (bucketMap.inverse().containsKey(hash)) {
            f = bucketMap.inverse().get(hash);
            PfF.Proxy.print("Found known bucket mapping: " + liquidName + " | " + f);
        } else {
            bucketMap.put(f, hash);
            PfF.Proxy.print("New bucket mapping: " + liquidName + " | " + f);
        }
        Field field = ReflectionHelper.getBucketField(liquidName);
        denLib.ReflectionHelper.setStaticField(field, new ItemLRBucket(ReflectionHelper.getBucketItemID(liquidName), l.itemID, StringUtils.getLocalString(liquidName)));
        ItemStack i = ReflectionHelper.getBucketItemStack(field);
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(l, LiquidContainerRegistry.BUCKET_VOLUME), i, LiquidContainerRegistry.EMPTY_BUCKET));
        LRLiquids.bucketStacks.put(l.toString(), i);
    }

    public void processLiquids() {
        for (LiquidStack l : LRLiquids.LRLiquids.values()) {
            processLiquid(l);
        }
        denLib.NBTUtils.saveObjectToNBTFile(bucketMapSave, this.bucketMap);
    }

    public static class ReflectionHelper {

        public static Field getBucketField(String name) {
            String n = denLib.StringUtils.removeSpaces("item" + name + "Bucket");
            return denLib.ReflectionHelper.getStaticField(LRItems.class.getName(), n);
        }
        
        public static int getBucketItemID(String name){
            String n = denLib.StringUtils.removeSpaces(name.toLowerCase()) + "_IronBucket_ItemID";
            return Integer.valueOf(denLib.ReflectionHelper.findStringInNestedClass(PfFTuning.class, n));
        }
        
        public static ItemStack getBucketItemStack(Field f){
            try{
                Object o = f.get(null);
                ItemLRBucket bucket = (ItemLRBucket) o;
                return bucket.getItemStack();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public static class StringUtils{
        
        public static String getLocalString(String name){
            return "item.pff." + denLib.StringUtils.removeSpaces(name.toLowerCase()) + "ironbucket.0.name";
        }
        
    }
}
