package denoflionsx.PluginsforForestry.Managers;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquidManager;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.denLib.denLib;
import java.util.HashMap;
import net.minecraftforge.liquids.LiquidStack;

public class PfFLiquidManager implements IPfFLiquidManager {

    public static final HashMap<String, LiquidStack> liquids = new HashMap();

    @Override
    public HashMap<String, LiquidStack> getEntireMap() {
        return liquids;
    }

    @Override
    public LiquidStack getNewLiquidStackByTag(String tag, int amount) {
        LiquidStack l = getLiquidStackByTag(tag);
        l.amount = amount;
        return l.copy();
    }

    @Override
    public LiquidStack getLiquidStackByTag(String tag) {
        if (liquids.get(tag) != null) {
            return liquids.get(tag);
        } else {
            return null;
        }
    }

    @Override
    public void registerLiquid(String name, LiquidStack liquid) {
        liquids.put(denLib.toLowerCaseNoSpaces(name), liquid);
        PfFManagers.Events.SystemEvents.raiseAlert("LiquidManager", name, liquid);
    }
}
