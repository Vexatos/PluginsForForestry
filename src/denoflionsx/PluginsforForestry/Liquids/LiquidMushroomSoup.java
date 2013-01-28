package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel;
import denoflionsx.PluginsforForestry.gfx.PfFGfxPackage;

public class LiquidMushroomSoup extends LiquidGenericBiogasFuel{
    
    public LiquidMushroomSoup(String name, String[] textures, int MJt, int BurnTime) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidMushroomSoup() {
        this("Mushroom Soup", new String[]{PfFGfxPackage.getPackage() + "/liquids/mushroomsoup.png",PfFGfxPackage.getPackage() + "/liquids/mushroomsoup_sparkles.png"},CoreTuning.Fuel.MushroomSoup_MJt,CoreTuning.Fuel.MushroomSoup_BurnTime);
    }

    @Override
    public IPfFLiquid createLiquid() {
        IPfFLiquid l = super.createLiquid();
    
        return l;
    }
    
    
    
}
