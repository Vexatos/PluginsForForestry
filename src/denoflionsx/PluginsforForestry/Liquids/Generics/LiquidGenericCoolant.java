package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;

public class LiquidGenericCoolant extends LiquidGeneric {

    private float cool;

    public LiquidGenericCoolant(String name, String[] textures, float cool) {
        super(name, textures);
        this.cool = cool;
    }

    @Override
    public IPfFLiquid createLiquid() {
        IPfFLiquid q = super.createLiquid();
        APIWrappers.buildcraft.combustion.registerCoolant(liquid, cool);
        return q;
    }
}
