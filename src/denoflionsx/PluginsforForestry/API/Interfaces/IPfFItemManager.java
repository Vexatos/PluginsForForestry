package denoflionsx.PluginsforForestry.API.Interfaces;

import java.util.HashMap;
import net.minecraft.item.ItemStack;

public interface IPfFItemManager {
    
    /*
     * Sends PfFEvent:
     * origin: ItemManager
     * msg: tag
     * obj: ItemStack
     */
    
    public void registerItem(String tag, ItemStack item);
    
    public ItemStack getItemByTag(String tag);
    
    public HashMap<String, ItemStack> getEntireItemMap();
    
}
