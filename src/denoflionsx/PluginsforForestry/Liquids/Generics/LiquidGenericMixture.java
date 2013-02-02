package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.denLib;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidGenericMixture extends LiquidGeneric {

    private LiquidStack[] components;

    public LiquidGenericMixture(LiquidStack[] components, String name, String[] textures) {
        super(name, textures);
        this.components = components;
    }

    @Override
    public IPfFLiquid createLiquid() {
        try {
            super.createLiquid();
            LiquidStack l = PfFManagers.Liquids.getNewLiquidStackByTag(denLib.toLowerCaseNoSpaces(name), 1);
            APIWrappers.buildcraft.refinery.registerRecipe(components[0], components[1], l, 5, 1);
        } catch (Exception ex) {
            PfF.Proxy.print("Something went wrong!");
            for (LiquidStack l : components){
                if (l == null){
                    PfF.Proxy.print("A mixture component is null in " + this.getClass().getSimpleName());
                }
            }
        }
        return this;
    }
}
