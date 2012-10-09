package denoflionsx.plugins.BlueSilkWorm.Gadget;

import buildcraft.api.core.Orientations;
import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormAccess;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormMothAccess;
import net.minecraft.src.ItemStack;

public class TileEntityIncubator extends PfFMachineTileEntity implements ISilkWormCocoonAccess, ISilkWormMothAccess, ISilkWormAccess {

    public TileEntityIncubator() {
        super();
    }

    public TileEntityIncubator(IPfFGadget gadget) {
        super(gadget);
    }

    @Override
    public void updateEntity() {
//        if (!hasWork){
//            int slot = findWorm();
//            if (slot != -1){
//                this.setInventorySlotContents(8, this.getStackInSlot(slot).copy());
//                this.setInventorySlotContents(slot, null);
//            }
//        }
        if (getWorm() != null) {
            if (getWorm().itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                hasWork = true;
                if (SilkWormHelper.isWormValid(getWorm())) {
                    SilkWormHelper.progressWorm(getWorm());
                } else {
                    SilkWormHelper.setupWorm(getWorm());
                }
            } else {
                hasWork = false;
            }
        } else {
            hasWork = false;
        }
    }

    public int findWorm() {
        for (int i = 0; i != 5; i++) {
            if (this.getStackInSlot(i) != null) {
                if (this.getStackInSlot(i).itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                    if (this.getStackInSlot(i).getItemDamage() != SilkWormGrowthStages.MOTH.getMeta()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public ItemStack getCocoon() {
        return stacks[9];
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

    @Override
    public int addItem(ItemStack stack, boolean doAdd, Orientations from) {
        int slot = getEmptySlot();
        if (slot == -1) {
            return 0;
        } else {
            if (stack.itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (doAdd) {
                    this.setInventorySlotContents(slot, stack);
                    return stack.stackSize;
                }
            }
        }
        return 0;
    }

    @Override
    public ItemStack[] extractItem(boolean doRemove, Orientations from, int maxItemCount) {
        return super.extractItem(doRemove, from, maxItemCount);
    }

    public int getEmptySlot() {
        for (int i = 0; i != (6); i++) {
            if (stacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int getOutput() {
        for (int i = 0; i != (20 - 12); i++) {
            if (stacks[i] != null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ItemStack getWorm() {
        return stacks[8];
    }

    @Override
    public boolean hasWorm() {
        if (getWorm() != null) {
            if (getWorm().itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (getWorm().getItemDamage() == SilkWormGrowthStages.WORM.getMeta()) {
                    return true;
                }
            }
        }
        return false;
    }
}
