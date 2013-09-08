package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Fluid;

import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import net.minecraftforge.fluids.Fluid;

public class PfFFluid extends Fluid {

    public PfFFluid(String fluidName) {
        super(fluidName);
    }

    @Override
    public String getLocalizedName() {
        return PfFTranslator.instance.translateKey(this.unlocalizedName);
    }
}
