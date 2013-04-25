package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids.LRLiquids;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import java.io.File;
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
        String hash = PfFLib.PffStringUtils.Hash(String.valueOf(l.itemID + "|" + l.itemMeta));
        if (bucketMap.inverse().containsKey(hash)) {
            f = bucketMap.inverse().get(hash);
        } else {
            bucketMap.put(f, hash);
        }
        ItemStack i = LRItems.ironBucket.createItemEntry(f);
        LanguageRegistry.addName(i, LRLiquids.getLiquidNameFromLiquidStack(l) + " " + PfFTranslator.instance.translateKey(LRItems.ironBucket.getUnlocalizedName() + ".0.name"));
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(l, LiquidContainerRegistry.BUCKET_VOLUME), i, LiquidContainerRegistry.EMPTY_BUCKET));
        LRLiquids.bucketStacks.put(l.toString(), i);
    }

    public void processLiquids() {
        for (LiquidStack l : LRLiquids.LRLiquids.values()) {
            processLiquid(l);
        }
        denLib.NBTUtils.saveObjectToNBTFile(bucketMapSave, this.bucketMap);
    }

}
