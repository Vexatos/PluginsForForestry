package denoflionsx.PluginsforForestry_PluginIc2.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry_PluginIc2.Config.Ic2Tuning;

@SqueezeLiquid(liquidname = "Radioactive Waste", validplants = "Radioactive Goo")
public class LiquidUranium extends LiquidGenericBiogasFuel{

    public LiquidUranium(int MJt, int BurnTime, String name, String[] textures) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidUranium(String name, String[] textures, int MJt, int BurnTime) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidUranium() {
        this("Radioactive Waste", new String[]{PfFConstants.Ic2Path + "liquid_radiowaste.png", PfFConstants.Ic2Path + "sparkles_radiowaste.png"}, Ic2Tuning.Fuels.RadioactiveWaste_MJt, Ic2Tuning.Fuels.RadioactiveWaste_BurnTime);
    }

}
