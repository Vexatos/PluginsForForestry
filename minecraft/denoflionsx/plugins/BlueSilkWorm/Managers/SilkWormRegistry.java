package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.core.core;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWorm;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormRegistry;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class SilkWormRegistry implements ISilkWormRegistry {

    public static final String msg = "Tried to register an invalid item ";
    public static ArrayList<ItemStack> worms = new ArrayList();
    public static ArrayList<ItemStack> cocoons = new ArrayList();
    public static ArrayList<ItemStack> moths = new ArrayList();

    @Override
    public void registerCocoon(ItemStack cocoon) {
        if (cocoon.getItem() instanceof ISilkWorm) {
            cocoons.add(cocoon);
        } else {
            core.print(msg + "as a cocoon!");
        }
    }

    @Override
    public void registerMoth(ItemStack moth) {
        if (moth.getItem() instanceof ISilkWorm) {
            moths.add(moth);
        } else {
            core.print(msg + "as a moth!");
        }
    }

    @Override
    public void registerWorm(ItemStack worm) {
        if (worm.getItem() instanceof ISilkWorm) {
            worms.add(worm);
        } else {
            core.print(msg + "as a worm!");
        }
    }

    @Override
    public boolean isItemStackCocoon(ItemStack potentialCocoon) {
        if (potentialCocoon == null) {
            return false;
        }
        for (ItemStack i : cocoons) {
            if (i.isItemEqual(potentialCocoon)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isItemStackMoth(ItemStack potentialMoth) {
        if (potentialMoth == null) {
            return false;
        }
        for (ItemStack i : moths) {
            if (i.isItemEqual(potentialMoth)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isItemStackWorm(ItemStack potentialWorm) {
        if (potentialWorm == null) {
            return false;
        }
        for (ItemStack i : worms) {
            if (i.isItemEqual(potentialWorm)) {
                return true;
            }
        }
        return false;
    }
}
