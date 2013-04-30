package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class LRIconManager {

    private BiMap<ItemStack, Icon> icons = HashBiMap.create();
    
    public void addIcon(ItemStack s, Icon c){
        icons.put(s, c);
    }
    
    public Icon getIcon(ItemStack i){
        for (ItemStack a : icons.inverse().values()){
            if (i.isItemEqual(a)){
                return icons.get(a);
            }
        }
        return null;
    }
}
