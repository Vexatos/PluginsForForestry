package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.denLib.denLib;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidGeneric implements IPfFLiquid {

    public static final String cat = "Liquid_Fuels";
    
    public String name;
    public String[] textures;
    public LiquidStack liquid;

    public LiquidGeneric(String name, String[] textures) {
        this.name = name;
        this.textures = textures;
    }

    @Override
    public String getTag() {
        return denLib.toLowerCaseNoSpaces(name);
    }

    @Override
    public IPfFLiquid createLiquid() {
        liquid = LRManagers.Liquids.registerLiquid(name, textures);
        PfFManagers.Liquids.registerLiquid(name,liquid);
        return this;
    }
}
