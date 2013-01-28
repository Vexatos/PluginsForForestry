package denoflionsx.LiquidRoundup.CreativeTab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RoundupTab extends CreativeTabs{

    public RoundupTab() {
        super("Liquid Roundup");
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Block.waterStill);
    }

    @Override
    public String getTranslatedTabLabel() {
        return getTabLabel();
    }

}
