package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormMothAccess;
import net.minecraft.src.ItemStack;

public class TileEntityIncubator extends PfFMachineTileEntity implements ISilkWormCocoonAccess, ISilkWormMothAccess{

    public TileEntityIncubator() {
        super();
    }

    public TileEntityIncubator(IPfFGadget gadget) {
        super(gadget);
    }

    @Override
    public void updateEntity() {
        if (this.getStackInSlot(0) != null) {
            if (stacks[0].itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (SilkWormHelper.isWormValid(stacks[0])) {
                    SilkWormHelper.progressWorm(stacks[0]);
                } else {
                    SilkWormHelper.setupWorm(stacks[0]);
                }
            }
        }
    }

    @Override
    public ItemStack getCocoon() {
        return stacks[0];
    }

    @Override
    public ItemStack getMoth() {
        return stacks[0];
    }

    @Override
    public boolean hasCocoon() {
        if (getCocoon() != null) {
            if (getCocoon().itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (getCocoon().getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasMoth() {
        if (getMoth() != null) {
            if (getMoth().itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (getMoth().getItemDamage() == SilkWormGrowthStages.MOTH.getMeta()) {
                    return true;
                }
            }
        }
        return false;
    }
}
