package denoflionsx.PluginsforForestry.API.Interfaces;

import net.minecraftforge.liquids.LiquidStack;

public interface IPfFLiquidMixManager {
    
    /*
     * Unused
     */
    
    public void registerReagent(String name, LiquidStack reagent);
    
    public void registerLiquid(String name, LiquidStack liquid);
    
    public void createMixtures();
    
    
}
