package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Fluid;

import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import net.minecraftforge.fluids.Fluid;

public class PfFFluid extends Fluid {

    private int color;

    public PfFFluid(String fluidName, int color) {
        super(fluidName);
        this.color = color;
    }

    @Override
    public String getLocalizedName() {
        return PfFTranslator.instance.translateKey(this.unlocalizedName);
    }

    @Override
    public int getColor() {
        return this.color;
    }
}
