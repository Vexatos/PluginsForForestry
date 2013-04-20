package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Managers;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;

public class LRLiquidManager{
    
    public void processLiquidFromDictionary(LiquidRegisterEvent event) {
        PluginLR.containers.createContainersForDictionaryLiquid(event);
    }
    
       
}
