package denoflionsx.PluginsforForestry.API.Interfaces;

import denoflionsx.PluginsforForestry.API.Objects.OmniPlantExternal;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantProduct;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;

public interface IPfFOmniPlantManager {

    /*
     * Sends PfFEvent:
     * origin: Omniplant
     * msg: item name
     * obj: OmniPlantProduct
     */
    
    public void registerPlant(ItemStack produce);
    
    public void registerPlantWithSeed(ItemStack produce, ItemStack seed);
    
    public ArrayList<OmniPlantExternal> getExternalPlants();

    public OmniPlantProduct getPlantByInternalID(int internalID);

    public int getInternalIDByPlant(ItemStack produce);

    public OmniPlantProduct[] getProductList();
}
