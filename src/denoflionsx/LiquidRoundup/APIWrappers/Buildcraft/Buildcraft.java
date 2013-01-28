package denoflionsx.LiquidRoundup.APIWrappers.Buildcraft;

import buildcraft.api.fuels.IronEngineCoolant;
import buildcraft.api.fuels.IronEngineFuel;
import buildcraft.api.recipes.RefineryRecipe;
import denoflionsx.LiquidRoundup.API.Annotations.Wrapper;
import denoflionsx.LiquidRoundup.Interfaces.IAPIWrapper;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import net.minecraftforge.liquids.LiquidStack;

@Wrapper
public class Buildcraft implements IAPIWrapper {

    public Refinery refinery = new Refinery();
    public IronEngine combustion = new IronEngine();
    private boolean hasWarned = false;

    @Override
    public void warning() {
        if (!hasWarned) {
            LiquidRoundup.Proxy.print("Buildcraft API not found!");
            hasWarned = true;
        }
    }

    public class Refinery {

        public void registerRecipe(LiquidStack l1, LiquidStack l2, LiquidStack result, int energy, int delay) {
            try {
                RefineryRecipe.registerRefineryRecipe(new RefineryRecipe(l1, l2, result, energy, delay));
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }

    public class IronEngine {

        public void registerFuel(LiquidStack fuel, int mjt, int burntime) {
            try {
                IronEngineFuel.fuels.add(new IronEngineFuel(fuel, mjt, burntime));
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }

        public void registerCoolant(LiquidStack fuel, float num) {
            try {
                IronEngineCoolant.coolants.add(new IronEngineCoolant(StackUtils.getNewStack(fuel, 1), num));
            } catch (NoClassDefFoundError ex) {
                warning();
            }
        }
    }
}
