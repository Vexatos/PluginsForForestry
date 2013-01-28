package denoflionsx.PluginsforForestry_PluginForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericBiogasFuel;
import denoflionsx.PluginsforForestry_PluginForestry.Config.ForestryTuning;
import denoflionsx.PluginsforForestry_PluginForestry.gfx.ForestryGfxPackage;

@SqueezeLiquid(liquidname = "Liquid Peat", validplants = "Peat")
public class LiquidPeat extends LiquidGenericBiogasFuel {

    public LiquidPeat(int MJt, int BurnTime, String name, String[] textures) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidPeat(String name, String[] textures, int MJt, int BurnTime) {
        super(MJt, BurnTime, name, textures);
    }

    public LiquidPeat() {
        this("Liquid Peat", new String[]{ForestryGfxPackage.getPackage() + "/liquids/liquid_peat.png", ForestryGfxPackage.getPackage() + "/liquids/sparkles_peat.png"}, ForestryTuning.Fuels.LiquidPeat_MJt,ForestryTuning.Fuels.LiquidPeat_BurnTime);
    }
}
