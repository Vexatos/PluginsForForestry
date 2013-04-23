package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import java.io.File;
import java.util.Arrays;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;

public class LRContainerManager {

    // Omg why didn't I know about BiMap earlier?!
    public BiMap<Integer, String> liquidMap = HashBiMap.create();
    public File c = new File(PfF.core.configDir.getAbsolutePath() + "/LiquidData.bin");

    public LRContainerManager() {
        if (c.exists()) {
            // read BiMap from NBT stored as a file.
            liquidMap = (BiMap<Integer, String>) denLib.NBTUtils.restoreObjectFromNBTFile(c);
        }
    }

    public void createContainersForDictionaryLiquid(LiquidRegisterEvent event) {
        // This is some crap to figure out what the last used ID is.
        // I'm sure there is a better way, but this is what I came up with at
        // 1 am.
        int ids[] = new int[liquidMap.size()];
        int temp = 0;
        for (Object o : liquidMap.keySet()) {
            Integer i = (Integer) o;
            ids[temp] = i;
            temp++;
        }
        Arrays.sort(ids);
        int f = 0;
        if (ids.length != 0) {
            f = ids[ids.length - 1];
        }
        f++;
        //------------
        String value = event.Liquid.itemID + "|" + event.Liquid.itemMeta + "|" + event.Name;
        if (liquidMap.inverse().containsKey(value)) {
            // Liquid exists in map.
            PfF.Proxy.print("Found known liquid " + event.Name + ".");
            f = liquidMap.inverse().get(value);
        } else {
            // Learn the liquid.
            PfF.Proxy.print("Learned about new liquid : " + event.Name + ".");
            liquidMap.put(f, value);
        }
        for (ItemMeta m : LRItems.containers.values()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("internal", value);
            ItemStack i = m.createItemEntry(f, tag);
            LanguageRegistry.addName(i, PfFLib.PffStringUtils.cleanLiquidNameFromEvent(event) + " " + PfFTranslator.instance.translateKey(m.getUnlocalizedName() + ".0.name"));
            LiquidContainerRegistry.registerLiquid(new LiquidContainerData(denLib.LiquidStackUtils.getNewStackCapacity(event.Liquid, LRItems.containerSize.get(m)), i, new ItemStack(m, 1, 0)));
        }
    }

    public void registerNewContainer(String name, String[] textures, int itemID, int capacity) {
        ItemMeta a = new ItemMeta(textures, itemID);
        a.createItemEntry(0);
        LRItems.containers.put(name, a);
        a.setUnlocalizedName("pff." + denLib.StringUtils.removeSpaces(name.toLowerCase()));
        PfF.Proxy.print("Created Liquid Container: " + a.getUnlocalizedName());
        LRItems.containerSize.put(a, capacity);
    }

    public void registerNewPfFContainer(String name, String[] textures, int itemID, int capacity) {
    }

    public void registerNewContainer(String name, String[] textures, int itemID) {
        this.registerNewContainer(name, textures, itemID, LiquidContainerRegistry.BUCKET_VOLUME);
    }
}
