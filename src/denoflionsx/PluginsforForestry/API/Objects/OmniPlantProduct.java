package denoflionsx.PluginsforForestry.API.Objects;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import net.minecraft.item.ItemStack;

public class OmniPlantProduct {
    
    private ItemStack produce;
    private int internalID;

    public OmniPlantProduct(ItemStack produce, int internalID) {
        this.produce = produce;
        this.internalID = internalID;
    }

    public ItemStack getProduce() {
        return produce;
    }

    public int getInternalID() {
        return internalID;
    }
    
    public ItemStack getSeed(){
        return new ItemStack(PfFManagers.Items.getItemByTag("omniplantseed").itemID,1,getInternalID());
    }
}
