package denoflionsx.PluginsforForestry_PluginRailcraft.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGeneric;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import denoflionsx.PluginsforForestry_PluginRailcraft.Core.PfFRailcraftCore;
import net.minecraft.item.ItemStack;

@SqueezeLiquid(liquidname = "Cactus Syrup", validplants = {"Cactus"})
public class LiquidCactusSyrup extends LiquidGeneric {

    public LiquidCactusSyrup(String name, String[] textures) {
        super(name, textures);
        int i = RailcraftTuning.Tuning.CactusSyrup_AmountPerSqueeze;
        if (RailcraftTuning.Tuning.CactusCoke_ThermalExpansionNerf) {
            i = i / 2;
            PfF.Proxy.print("Cactus Coke nerfed due to the presence of Thermal Expansion.");
        }
        PfFManagers.Squeeze.registerSqueezable("Cactus", new ItemStack(PfFRailcraftCore.sc, 1, 4), i);
    }

    public LiquidCactusSyrup() {
        this("Cactus Syrup", new String[]{PfFConstants.RCpath + "liquid_cactussyrup.png", PfFConstants.RCpath + "sparkles_cactussyrup.png"});
    }
}
