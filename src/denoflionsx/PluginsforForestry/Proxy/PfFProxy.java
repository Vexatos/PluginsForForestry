package denoflionsx.PluginsforForestry.Proxy;

import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.BackpackUtils;
import denoflionsx.denLib.Mod.denLibMod;
import denoflionsx.denLib.denLib;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class PfFProxy implements IPfFProxy {

    @Override
    public void addSheetToMap(String sheet) {
        
    }

    @Override
    public ArrayList<ItemStack> loadList(Configuration config, String category, ArrayList<ItemStack> list) {
        list.clear();
        ConfigCategory c = config.getCategory("backpacks." + category);
        if (c != null) {
            for (Property p : c.values()) {
                String v = p.value;
                String[] split = v.split(",");
                int id = Integer.valueOf(split[0]);
                int meta = Integer.valueOf(split[1]);
                ItemStack item = new ItemStack(id, 1, meta);
                list.add(item);
                try {
                    //this.print("Added " + item.getItemName() + " to backpack " + category);
                } catch (Exception ex) {
                }
            }
        }
        PfF.Proxy.print(list.size() + " items added to backpack " + category + ".");
        return list;
    }

    @Override
    public void saveList(ArrayList<ItemStack> list, String category) {
    }

    @Override
    public void getAllReferences(String clazz, ArrayList<ItemStack> add) {
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public void makeEntityDropItem(Entity entity, ItemStack item) {
        entity.dropItem(item.itemID, item.stackSize);
    }

    @Override
    public String getConfigDir() {
        return denLibMod.proxy.getConfigDir() + "PluginsforForestry" + File.separator;
    }

    @Override
    public String preloadTextures(String texture) {
        return denLibMod.proxy.preloadTextures(texture);
    }

    @Override
    public void print(String msg) {
        denLib.print("[PfF]: " + msg);
    }

    @Override
    public void sendMessageToPlayer(String msg) {
        denLibMod.proxy.sendMessageToPlayer(msg);
    }
}
