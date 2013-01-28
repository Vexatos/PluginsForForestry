package denoflionsx.PluginsforForestry_PluginRailcraft.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGeneric;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginRailcraft.Config.RailcraftTuning;
import denoflionsx.denLib.denLib;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@SqueezeLiquid(liquidname = "Sugar Syrup", validplants = {"Sugar"})
public class LiquidSugarSyrup extends LiquidGeneric {

    public LiquidSugarSyrup(String name, String[] textures) {
        super(name, textures);
        int i = RailcraftTuning.Tuning.SugarSyrup_AmountPerSqueeze;
        if (denLib.LOADER.FML.isModLoaded("ThermalExpansion") && RailcraftTuning.Tuning.SugarCoke_ThermalExpansionNerf) {
            PfF.Proxy.print("Sugar Coke nerfed due to the presence of Thermal Expansion.");
            i = i / 2;
        }
        PfFManagers.Squeeze.registerSqueezable("Sugar", new ItemStack(Item.sugar), i);
    }

    public LiquidSugarSyrup() {
        this("Sugar Syrup", new String[]{Constants.path + "sugar.png", Constants.path + "sugar_sparkles.png"});
    }

    @Override
    public IPfFLiquid createLiquid() {
        return super.createLiquid();
    }
}
