package denoflionsx.PluginsforForestry.Tab;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PfFTab extends CreativeTabs {

    private ItemStack icon;

    public PfFTab() {
        super("Plugins for Forestry");
        icon = new ItemStack(PluginLR.woodenBucket);
    }

    @Override
    public ItemStack getIconItemStack() {
        return icon;
    }

    public String getTranslatedTabLabel() {
        return "Plugins for Forestry";
    }
}
