package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;

public class LiquidMushroomSoup extends LiquidGenericBiogasFuel {

    public LiquidMushroomSoup(String name, String[] textures, int MJt, int BurnTime) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidMushroomSoup() {
        this("Mushroom Soup", new String[]{PfFConstants.PfFPath + "mushroomsoup.png", PfFConstants.PfFPath + "mushroomsoup_sparkles.png"}, CoreTuning.Fuel.MushroomSoup_MJt, CoreTuning.Fuel.MushroomSoup_BurnTime);
    }

    @Override
    public IPfFLiquid createLiquid() {
        IPfFLiquid l = super.createLiquid();

        return l;
    }
}
