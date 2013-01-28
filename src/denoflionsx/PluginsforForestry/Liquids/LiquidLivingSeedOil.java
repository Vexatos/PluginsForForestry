package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericMixture;
import denoflionsx.PluginsforForestry.Utils.ForestryLiquids;
import denoflionsx.PluginsforForestry.gfx.PfFGfxPackage;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidLivingSeedOil extends LiquidGenericMixture{

    public LiquidLivingSeedOil(LiquidStack[] components, String name, String[] textures) {
        super(components, name, textures);
    }

    public LiquidLivingSeedOil(String name, String[] textures, LiquidStack[] components) {
        this(components, name, textures);
    }

    public LiquidLivingSeedOil() {
        this("Living Seedoil", new String[]{PfFGfxPackage.getPackage() + "/liquids/livingseedoil.png",PfFGfxPackage.getPackage() + "/liquids/livingseedoil_sparkles.png"},new LiquidStack[]{new LiquidStack(ForestryLiquids.BIOMASS.getStack().itemID,2,ForestryLiquids.BIOMASS.getStack().getItemDamage()), new LiquidStack(ForestryLiquids.SEEDOIL.getStack().itemID,1,ForestryLiquids.SEEDOIL.getStack().getItemDamage())});
    }

}
