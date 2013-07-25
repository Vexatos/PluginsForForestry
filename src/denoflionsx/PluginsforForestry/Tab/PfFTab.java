package denoflionsx.PluginsforForestry.Tab;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class PfFTab extends CreativeTabs{

    public PfFTab() {
        super("Plugins for Forestry");
    }

    @Override
    public ItemStack getIconItemStack() {
        if (LRItems.ItemStackWoodenBucketEmpty != null){
            return LRItems.ItemStackWoodenBucketEmpty;
        }else{
            return LiquidContainerRegistry.EMPTY_BUCKET;
        }
    }
           public String getTranslatedTabLabel(){
		   return "Plugins for Forestry";
	   }
}
