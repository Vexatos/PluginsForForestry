package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFItemManager;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class PfFFoodManager implements IPfFItemManager{
    
    public static final HashMap<String, ItemStack> stacks = new HashMap();

    @Override
    public HashMap<String, ItemStack> getEntireItemMap() {
        return stacks;
    }

    @Override
    public ItemStack getItemByTag(String tag) {
        if (stacks.get(tag) != null){
            return stacks.get(tag);
        }else{
            PfF.Proxy.print("Food Manager failed to get item " + tag);
            return null;
        }
    }

    @Override
    public void registerItem(String tag, ItemStack item) {
        stacks.put(tag,item);
        PfFManagers.Events.SystemEvents.raiseAlert("FoodManager", tag, item);
    }
   
}
