package denoflionsx.LiquidRoundup.APIWrappers.ThermalExpansion;

import denoflionsx.LiquidRoundup.API.Annotations.Wrapper;
import denoflionsx.LiquidRoundup.Interfaces.IAPIWrapper;
import denoflionsx.LiquidRoundup.LiquidRoundup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import thermalexpansion.api.crafting.CraftingManagers;

@Wrapper
public class ThermalExpansion implements IAPIWrapper {

    public pulvManager pulv = new pulvManager();
    public crucibleManager crucible = new crucibleManager();
    public TransposerManager transposer = new TransposerManager();
    private boolean hasWarned = false;

    @Override
    public void warning() {
        if (!hasWarned) {
            LiquidRoundup.Proxy.print("Thermal Expansion API not found!");
            hasWarned = true;
        }
    }

    public class TransposerManager {

        public static final int maxMJ = 4800;

        public boolean addExtractionRecipe(int energy, ItemStack input, ItemStack output, LiquidStack liquid, boolean reversible) {
            try {
                if (energy > maxMJ) {
                    energy = maxMJ;
                }
                CraftingManagers.transposerManager.addExtractionRecipe(energy, input, output, liquid, 0, reversible);
            } catch (NoClassDefFoundError ex) {
                warning();
            } catch (java.lang.NullPointerException ex) {
                if (input == null) {
                    try {
                        throw new Exception("Transposer input is null! This is a bug.");
                    } catch (Exception q) {
                        q.printStackTrace();
                    }
                }
                if (liquid == null) {
                    try {
                        throw new Exception("Transposer liquid is null! This is a bug.");
                    } catch (Exception q) {
                        q.printStackTrace();
                    }
                }
            }
            return false;
        }

        public boolean addFillRecipe(int energy, ItemStack input, ItemStack output, LiquidStack liquid, boolean reversible) {
            if (energy > maxMJ) {
                energy = maxMJ;
            }
            try {
                return CraftingManagers.transposerManager.addFillRecipe(energy, input, output, liquid, reversible);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return false;
        }
    }

    public class pulvManager {

        public boolean addRecipe(int energy, ItemStack input, ItemStack primary, ItemStack secondary, int secondaryChance) {
            try {
                return CraftingManagers.pulverizerManager.addRecipe(energy, input, primary, secondary, secondaryChance);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return false;
        }

        public boolean addRecipe(int energy, ItemStack input, ItemStack primary, ItemStack secondary) {
            try {
                return CraftingManagers.pulverizerManager.addRecipe(energy, input, primary, secondary);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return false;
        }

        public boolean addRecipe(int energy, ItemStack input, ItemStack primary) {
            try {
                return CraftingManagers.pulverizerManager.addRecipe(energy, input, primary);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return false;
        }
    }

    public class crucibleManager {

        public boolean addRecipe(int energy, ItemStack input, LiquidStack output) {
            try {
                return CraftingManagers.crucibleManager.addRecipe(energy, input, output);
            } catch (NoClassDefFoundError ex) {
                warning();
            }
            return false;
        }
    }
}
