package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import net.minecraft.item.ItemStack;

public class ItemBarrel extends ItemContainerBase {

    public ItemBarrel(int itemID, int capacity, String unloc, String tag, String icon) {
        super(itemID, capacity, unloc, tag, icon);
        PluginLR.stacks.put("barrel", new ItemStack(this));
    }
}
