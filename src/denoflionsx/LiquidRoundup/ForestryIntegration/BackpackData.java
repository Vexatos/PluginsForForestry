package denoflionsx.LiquidRoundup.ForestryIntegration;

import forestry.api.storage.IBackpackDefinition;
import net.minecraft.item.ItemStack;

public class BackpackData {

    private int[] ids;
    private IBackpackDefinition backpack;
    private ItemStack material;

    public BackpackData(int[] ids, IBackpackDefinition backpack, ItemStack material) {
        this.ids = ids;
        this.backpack = backpack;
        this.material = material;
    }

    public IBackpackDefinition getBackpack() {
        return backpack;
    }

    public int[] getIds() {
        return ids;
    }

    public ItemStack getMaterial() {
        return material;
    }

}
