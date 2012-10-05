package denoflionsx.plugins.BluesFood.Items;

import denoflionsx.items.multiItem;
import net.minecraft.src.CreativeTabs;

public class multiItemFood extends multiItem{

    public multiItemFood(int par1, String name) {
        super(par1, name);
        this.setTabToDisplayOn(CreativeTabs.tabFood);
    }

    @Override
    public String getTextureFile() {
        return ItemFoods.spritesheet;
    }
}
