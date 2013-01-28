package denoflionsx.PluginsforForestry_PluginRailcraft.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGeneric;

@SqueezeLiquid(liquidname = "Sulfuric Acid", validplants = {"Sulfur"})
public class LiquidSulfuricAcid extends LiquidGeneric{

    public LiquidSulfuricAcid(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidSulfuricAcid() {
        super("Sulfuric Acid", new String[]{Constants.path + "acid.png", Constants.path + "acid_sparkles.png"});
    }

    @Override
    public IPfFLiquid createLiquid() {
        return super.createLiquid();
    }

}
