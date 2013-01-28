package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFOmniPlantManager;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantExternal;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantProduct;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Property;

public class PfFOmniPlantManager implements IPfFOmniPlantManager {
    
    private static int current = -1;
    private static final String cat = "core.OmniPlant_Internal_IDs".toLowerCase();
    public static OmniPlantProduct[] products = new OmniPlantProduct[1024];
    public static ArrayList<OmniPlantExternal> external = new ArrayList();
    
    static {
        if (PfF.Core.config.getCategory(cat) != null) {
            ConfigCategory c = PfF.Core.config.getCategory(cat);
            PfF.Core.config.addCustomCategoryComment(cat, "DO NOT CHANGE THESE!");
            Map<String, Property> map = c.getValues();
            ArrayList<Integer> list = new ArrayList();
            for (Property p : map.values()) {
                int id = p.getInt();
                list.add(id);
            }
            Collections.sort(list);
            if (!list.isEmpty()) {
                current = list.get(list.size() - 1) - 1;
            }
        }
    }

    @Override
    public ArrayList<OmniPlantExternal> getExternalPlants() {
        return external;
    }

    @Override
    public void registerPlantWithSeed(ItemStack produce, ItemStack seed) {
        external.add(new OmniPlantExternal(produce,seed));
    }

    @Override
    public OmniPlantProduct[] getProductList() {
        return products;
    }

    @Override
    public int getInternalIDByPlant(ItemStack produce) {
        for (OmniPlantProduct p : products){
            if (p.getProduce().isItemEqual(produce)){
                return p.getInternalID();
            }
        }
        return -1;
    }
    
    @Override
    public OmniPlantProduct getPlantByInternalID(int internalID) {
        if (products[internalID] != null) {
            return products[internalID];
        } else {
            return null;
        }
    }

    @Override
    public void registerPlant(ItemStack produce) {
        int id = PfF.Core.config.get(cat, produce.getItemName() + "_InternalID", getNewID()).getInt();
        OmniPlantProduct p = new OmniPlantProduct(produce, id);
        products[id] = p;
        PfFManagers.Events.SystemEvents.raiseAlert("Omniplant", produce.getItemName(), p);
        PfF.Proxy.print("Registered Omniplant target: " + produce.itemID + "," + produce.getItemDamage() + " on internal id " + id + ". Item is " + p.getProduce().getItemName());
    }

    public int getNewID() {
        current++;
        return current;
    }
}
