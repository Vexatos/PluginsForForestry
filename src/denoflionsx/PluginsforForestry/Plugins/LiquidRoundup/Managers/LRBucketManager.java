package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemMetaBucket;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class LRBucketManager {

    private ItemMetaBucket entry;
    private String[] blackList;

    public LRBucketManager(ItemMetaBucket entry, String[] blackList) {
        this.entry = entry;
        this.blackList = blackList;
        this.processLiquids();
    }

    public final void processLiquids() {
        for (LiquidDictionary.LiquidRegisterEvent e : PluginLR.events) {
            this.processLiquid(e);
        }
    }

    public void processLiquid(LiquidDictionary.LiquidRegisterEvent e) {
        if (LiquidContainerRegistry.fillLiquidContainer(e.Liquid, this.entry.getEmpty()) != null) {
            return;
        }
        for (String s : this.blackList) {
            if (s.equals(e.Name)) {
                return;
            }
        }
        String lname = PfFLib.PffStringUtils.getItemName(e.Liquid.asItemStack());
        if (lname.equals("")) {
            lname = PfFLib.PffStringUtils.cleanLiquidNameFromEvent(e);
        }
        NBTTagCompound check = new NBTTagCompound();
        e.Liquid.writeToNBT(check);
        ItemStack f = this.entry.register(LRContainerManager.metaMap.get(e.Name), lname + " Bucket", e.Liquid);
        f.setTagCompound(check);
        if (this.entry.equals(LRItems.woodenBucket)) {
            LRItems.woodenBucketstacks.put(e.Liquid.itemID, f);
        } else {
            LRItems.bucketStacks.put(e.Liquid.itemID, f);
        }
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(e.Liquid, LiquidContainerRegistry.BUCKET_VOLUME), f, this.entry.getEmpty()));
    }
}
