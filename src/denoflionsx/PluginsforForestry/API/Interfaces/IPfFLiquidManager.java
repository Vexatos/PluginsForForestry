package denoflionsx.PluginsforForestry.API.Interfaces;

import java.util.HashMap;
import net.minecraftforge.liquids.LiquidStack;

public interface IPfFLiquidManager {
    
    /*
     * Sends PfFEvent:
     * origin: LiquidManager
     * msg: name
     * obj: LiquidStack
     */
    
    public void registerLiquid(String name, LiquidStack liquid);
    
    public LiquidStack getLiquidStackByTag(String tag);
    
    public LiquidStack getNewLiquidStackByTag(String tag, int amount);
    
    public HashMap<String, LiquidStack> getEntireMap();
    
}
