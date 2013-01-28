package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFItemManager;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class PfFItemManager implements IPfFItemManager{
    
    public static final HashMap<String, ItemStack> items = new HashMap();

    @Override
    public HashMap<String, ItemStack> getEntireItemMap() {
        return items;
    }

    @Override
    public ItemStack getItemByTag(String tag) {
        if (items.get(tag) != null){
            return items.get(tag);
        }else{
            PfF.Proxy.print("Item Manager failed to get item " + tag);
            return null;
        }
    }

    @Override
    public void registerItem(String tag, ItemStack item) {
        items.put(tag,item);
        PfFManagers.Events.SystemEvents.raiseAlert("ItemManager", tag, item);
    }
}
