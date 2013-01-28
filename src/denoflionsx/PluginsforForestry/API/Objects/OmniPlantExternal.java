package denoflionsx.PluginsforForestry.API.Objects;

import net.minecraft.item.ItemStack;

public class OmniPlantExternal {
    
    private ItemStack product;
    private ItemStack seed;

    public OmniPlantExternal(ItemStack product, ItemStack seed) {
        this.product = product;
        this.seed = seed;
    }

    public ItemStack getProduct() {
        return product;
    }

    public ItemStack getSeed() {
        return seed;
    }
}
