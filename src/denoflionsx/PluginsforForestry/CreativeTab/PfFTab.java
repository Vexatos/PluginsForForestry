package denoflionsx.PluginsforForestry.CreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class PfFTab extends CreativeTabs {

    public PfFTab() {
        super("PfF");
    }

    @Override
    public ItemStack getIconItemStack() {
        return PfFTabIcon.iconStack;
    }

    @Override
    public String getTabLabel() {
        return "Plugins for Forestry";
    }

    @Override
    public String getTranslatedTabLabel() {
        return getTabLabel();
    }
}
