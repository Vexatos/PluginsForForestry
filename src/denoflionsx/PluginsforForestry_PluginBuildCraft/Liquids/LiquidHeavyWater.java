package denoflionsx.PluginsforForestry_PluginBuildCraft.Liquids;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericCoolant;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;
import denoflionsx.PluginsforForestry_PluginBuildCraft.Config.BuildCraftTuning;
import net.minecraft.block.Block;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidHeavyWater extends LiquidGenericCoolant {

    public LiquidHeavyWater(String name, String[] textures, float cool) {
        super(name, textures, cool);
    }

    public LiquidHeavyWater() {
        super("Heavy Water", new String[]{PfFConstants.BCPath + "heavywater.png", PfFConstants.BCPath + "heavywater_sparkles.png"}, BuildCraftTuning.Coolants.HeavyWater_Coolant);
    }

    @Override
    public IPfFLiquid createLiquid() {
        try {
            IPfFLiquid q = super.createLiquid();
            if (liquid == null){
                PfF.Proxy.print("Something went wrong with Heavy Water creation.");
                return q;
            }
            APIWrappers.forestry.still.registerRecipe(new LiquidStack(Block.waterStill, 10), StackUtils.getNewStack(this.liquid, 10));
            APIWrappers.buildcraft.refinery.registerRecipe(new LiquidStack(Block.waterStill, 10), null, StackUtils.getNewStack(liquid, 10), 1, 1);
            return q;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
