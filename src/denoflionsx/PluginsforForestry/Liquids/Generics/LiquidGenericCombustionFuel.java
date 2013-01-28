package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;

public class LiquidGenericCombustionFuel extends LiquidGeneric {

    private int MJt;
    private int BurnTime;
    
    public LiquidGenericCombustionFuel(String name, String[] textures, int MJt, int BurnTime) {
        super(name, textures);
        this.MJt = MJt;
        this.BurnTime = BurnTime;
    }

    @Override
    public IPfFLiquid createLiquid() {
        IPfFLiquid q = super.createLiquid();
         APIWrappers.buildcraft.combustion.registerFuel(liquid, this.MJt, this.BurnTime);
        return q;
    }
    
    
}
