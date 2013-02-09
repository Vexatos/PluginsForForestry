package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericMixture;
import denoflionsx.PluginsforForestry.Utils.ForestryLiquids;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidLivingSeedOil extends LiquidGenericMixture{

    public LiquidLivingSeedOil(LiquidStack[] components, String name, String[] textures) {
        super(components, name, textures);
    }

    public LiquidLivingSeedOil(String name, String[] textures, LiquidStack[] components) {
        this(components, name, textures);
    }

    public LiquidLivingSeedOil() {
        this("Living Seedoil", new String[]{PfFConstants.PfFPath + "livingseedoil.png", PfFConstants.PfFPath + "livingseedoil_sparkles.png"},new LiquidStack[]{ForestryLiquids.BIOMASS.getLiquidStack(), ForestryLiquids.SEEDOIL.getLiquidStack()});
    }

}
