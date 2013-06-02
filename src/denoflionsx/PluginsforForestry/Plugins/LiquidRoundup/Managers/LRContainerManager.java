package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.ItemContainer;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import java.io.File;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class LRContainerManager {

    public static BiMap<String, Integer> metaMap = HashBiMap.create();
    private static final File save = new File(PfF.core.mappingsDir.getAbsolutePath() + "/MetaMap.BiMap");

    public LRContainerManager() {
        if (save.exists()) {
            metaMap = denLib.FileUtils.readBiMapFromFile(save);
        }
        WorldEventHandler.registerHandler(new saveHandler());
    }

    public void registerContainer(ItemStack empty, ItemContainer c, String name, int capacity, String[] blackList) {
        for (LiquidDictionary.LiquidRegisterEvent e : PluginLR.events) {
            if (e.Liquid == null) {
                PfF.Proxy.severe("A null liquid was found in the dictionary. Name: " + e.Name);
                continue;
            }
            //----
            if (LiquidContainerRegistry.fillLiquidContainer(e.Liquid, empty) != null) {
                continue;
            }
            boolean isBlacklisted = false;
            if (blackList != null) {
                for (String s : blackList) {
                    if (s.equals(e.Name)) {
                        isBlacklisted = true;
                    }
                }
            }
            if (isBlacklisted) {
                continue;
            }
            if (metaMap.get(e.Name) == null) {
                PfF.Proxy.print("Found new liquid: " + e.Name);
                int m = PfFLib.MathUtils.getLastID(metaMap.inverse());
                metaMap.put(e.Name, m);
            }
            int meta = metaMap.get(e.Name);
            String lname = PfFLib.PffStringUtils.getItemName(e.Liquid.asItemStack());
            if (lname.equals("")) {
                lname = PfFLib.PffStringUtils.cleanLiquidNameFromEvent(e);
            }
            if (lname.equals("Lava")) {
                if (c.madeOf.isDoesMelt()) {
                    continue;
                }
            }
            ItemStack i = c.createItemEntry(meta, lname + " " + name);
            if (lname.equals(PfFLib.PffStringUtils.error)) {
                i.stackTagCompound = new NBTTagCompound();
                NBTTagCompound t = new NBTTagCompound();
                t.setString("ohshit", "Please contact denoflions on MCF");
                i.stackTagCompound.setCompoundTag("info", t);
            }
            // Check data before registering.
            if (i == null) {
                PfF.Proxy.severe("Failed to create container!");
                continue;
            }
            if (empty == null) {
                PfF.Proxy.severe("Empty container is null!");
                continue;
            }
            if (capacity <= 0) {
                PfF.Proxy.severe("Capacity is invalid!");
                continue;
            }
            LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(e.Liquid, capacity), i, empty));
        }
    }

    public class saveHandler implements IdenWorldEventHandler {

        @Override
        public void onWorldLoaded() {
        }

        @Override
        public void onWorldEnded() {
            denLib.FileUtils.saveBiMapToFile(metaMap, save);
            WorldEventHandler.unregisterHandler(this);
        }
    }
}
