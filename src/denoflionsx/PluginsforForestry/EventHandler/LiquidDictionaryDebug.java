package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import net.minecraftforge.liquids.LiquidDictionary;

public class LiquidDictionaryDebug implements IdenWorldEventHandler {

    @Override
    public void onWorldLoaded() {
        PfF.Proxy.print("Printing Liquid Dictionary to console...");
        for (String s : LiquidDictionary.getLiquids().keySet()) {
            PfF.Proxy.print(s);
        }
        WorldEventHandler.unregisterHandler(this);
    }

    @Override
    public void onWorldEnded() {
    }
}
