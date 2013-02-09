package denoflionsx.PluginsforForestry_PluginRailcraft.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGeneric;

@SqueezeLiquid(liquidname = "Sulfuric Acid", validplants = {"Sulfur"})
public class LiquidSulfuricAcid extends LiquidGeneric{

    public LiquidSulfuricAcid(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidSulfuricAcid() {
        super("Sulfuric Acid", new String[]{PfFConstants.RCpath + "acid.png", PfFConstants.RCpath + "acid_sparkles.png"});
    }

    @Override
    public IPfFLiquid createLiquid() {
        return super.createLiquid();
    }

}
