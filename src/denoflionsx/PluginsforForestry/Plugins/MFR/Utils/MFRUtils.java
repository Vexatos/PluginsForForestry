package denoflionsx.PluginsforForestry.Plugins.MFR.Utils;

import denoflionsx.PluginsforForestry.Utils.UtilsTemplate;
import powercrystals.minefactoryreloaded.api.FarmingRegistry;
import powercrystals.minefactoryreloaded.api.ILiquidDrinkHandler;

public class MFRUtils extends UtilsTemplate {

    public MFRUtils() {
        super("Minefactory Reloaded");
    }

    public void registerDrinkable(int id, Object o) {
        try {
            FarmingRegistry.registerLiquidDrinkHandler(id, (ILiquidDrinkHandler) o);
        } catch (NoClassDefFoundError ex) {
            this.doAlert();
        }
    }
}
