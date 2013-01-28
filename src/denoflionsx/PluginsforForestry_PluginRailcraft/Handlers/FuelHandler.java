package denoflionsx.PluginsforForestry_PluginRailcraft.Handlers;

import denoflionsx.PluginsforForestry.Interfaces.IPfFFuelHandler;
import denoflionsx.denLib.denLib;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IPfFFuelHandler{

    public final HashMap<String, Integer> fuels = new HashMap();

    @Override
    public void registerFuel(ItemStack fuel, int burn) {
        String hash = denLib.Hash(fuel.itemID + " | " + fuel.getItemDamage());
        fuels.put(hash, burn);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        String hash = denLib.Hash(fuel.itemID + " | " + fuel.getItemDamage());
        if (fuels.get(hash) != null) {
            return fuels.get(hash);
        } else {
            return 0;
        }
    }
}
