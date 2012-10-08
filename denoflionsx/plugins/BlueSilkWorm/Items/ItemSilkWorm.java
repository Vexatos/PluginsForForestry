package denoflionsx.plugins.BlueSilkWorm.Items;

import denoflionsx.items.multiItem;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import java.util.List;
import net.minecraft.src.ItemStack;

public class ItemSilkWorm extends multiItem {

    public ItemSilkWorm(int par1, String name) {
        super(par1, name);
        this.setMaxStackSize(1);
        this.add("Silk Worm", SilkWormGrowthStages.WORM.getMeta(), SilkWormGrowthStages.WORM.getTexture());
        this.add("Silk Cocoon",SilkWormGrowthStages.COCOON.getMeta(),SilkWormGrowthStages.COCOON.getTexture());
        this.add("Silk Moth",SilkWormGrowthStages.MOTH.getMeta(),SilkWormGrowthStages.MOTH.getTexture());
        this.add("Dead Worm",SilkWormGrowthStages.DEAD_WORM.getMeta(),SilkWormGrowthStages.DEAD_WORM.getTexture());
        this.add("Zombie Worm",SilkWormGrowthStages.ZOMBIE_WORM.getMeta(),SilkWormGrowthStages.ZOMBIE_WORM.getTexture());
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, List par2List) {
        if (par1ItemStack.stackTagCompound != null){
            par2List.add("Gender: " + SilkWormHelper.getWormGender(par1ItemStack));
            par2List.add("Worm Stage Length: " + SilkWormHelper.getWormCocoonCategory(par1ItemStack));
            par2List.add("Cocoon Stage Length: " + SilkWormHelper.getWormMothCategory(par1ItemStack));
        }
    }

    @Override
    public boolean getShareTag() {
        return true;
    }
}
