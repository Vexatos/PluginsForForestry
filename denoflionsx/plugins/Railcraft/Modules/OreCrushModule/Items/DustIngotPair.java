package denoflionsx.plugins.Railcraft.Modules.OreCrushModule.Items;

import net.minecraft.src.ItemStack;

public class DustIngotPair {
    
    private ItemStack dust;
    private ItemStack ingot;
    private ItemStack ore;

    public DustIngotPair(ItemStack dust, ItemStack ingot, ItemStack ore) {
        this.dust = dust;
        this.ingot = ingot;
        this.ore = ore;
    }

    public ItemStack getDust() {
        return dust;
    }

    public ItemStack getIngot() {
        return ingot;
    }

    public ItemStack getOre() {
        return ore;
    }

}
