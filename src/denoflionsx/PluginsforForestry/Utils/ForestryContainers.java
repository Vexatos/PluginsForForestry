package denoflionsx.PluginsforForestry.Utils;

import forestry.api.core.ItemInterface;
import net.minecraft.item.ItemStack;

public enum ForestryContainers {
    
    CAPSULE("waxCapsule"),
    CAPSULE_RED("refractoryEmpty"),
    CAN("canEmpty");
    
    private ItemStack container;

    private ForestryContainers(String tag) {
        this.container = ItemInterface.getItem(tag);
    }

    public ItemStack getContainer() {
        return container;
    }
}
