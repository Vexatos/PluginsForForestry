package denoflionsx.PluginsforForestry.Plugins.Furnace;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Dictionary.Liquids.Engines.EngineFuel;
import denoflionsx.PluginsforForestry.Events.EngineEvent;
import denoflionsx.PluginsforForestry.Events.EngineEventHandler;
import denoflionsx.PluginsforForestry.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRItems;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import java.util.HashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class PluginFurnace implements IPfFPlugin, IFuelHandler {

    private boolean load = false;
    private HashMap<String, Integer> fuels = new HashMap();

    @Override
    public void onPreLoad() {
        load = true;
    }

    @Override
    public void onLoad() {
        if (load) {
            EngineEventHandler.instance.register(this);
        }
    }

    @Override
    public void onPostLoad() {
        GameRegistry.registerFuelHandler(this);
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuels.get(fuel.itemID + "|" + fuel.getItemDamage()) != null) {
            return fuels.get(fuel.itemID + "|" + fuel.getItemDamage());
        }
        return 0;
    }

    @PfFSubscribe
    public void onEvent(EngineEvent e) {
        if (load) {
            if (PfFTuning.getBool(PfFTuning.Furnace.fuel_LiquidContainersForFurnace)) {
                if (Loader.isModLoaded("Forestry")) {
                    PfF.Proxy.print("Scanning biogas hashmap...");
                    for (EngineFuel f : Forestry.getFuelMap()) {
                        for (ItemStack c : PfFLib.LiquidUtils.getAllContainersForLiquid(LiquidDictionary.getLiquid(f.getLiquidTag(), LiquidContainerRegistry.BUCKET_VOLUME))) {
                            int value = (f.getBurnTime());
                            int x = PfFTuning.getInt(PfFTuning.Furnace.fuel_LiquidContainerForFurnace_NerfPercent);
                            value *= x;
                            value /= 100;
                            if (PfFLib.LiquidUtils.getEmptyContainer(c).isItemEqual(LRItems.barrelEmpty)) {
                                value = value * 10;
                            }
                            fuels.put(c.itemID + "|" + c.getItemDamage(), value);
                        }
                    }
                }
            }
        }
    }
}
