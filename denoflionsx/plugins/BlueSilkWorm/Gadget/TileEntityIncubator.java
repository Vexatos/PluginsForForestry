package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.API.PfFManagers;
import denoflionsx.Interfaces.IPfFTrigger;
import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.core.core;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.Buildcraft.Triggers.Triggers;
import java.util.LinkedList;

public class TileEntityIncubator extends PfFMachineTileEntity implements ISilkWormCocoonAccess, IPfFTrigger {

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
                    id = SilkWormHelper.getWormLifeSpanLabel(stacks[0]);
                    time = String.valueOf(SilkWormHelper.getWormLifeSpanInt(stacks[0]));
                    if (stopCocoon && stacks[0].getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()) {
                        core.print("Stop!");
                    } else {
                        SilkWormHelper.progressWorm(stacks[0]);
                    }
                } else {
                    SilkWormHelper.setupWorm(stacks[0]);
                }
            }
        }
    }

    @Override
    public LinkedList getCustomTriggers() {
        LinkedList a = new LinkedList();
        a.add(Triggers.hasCocoon);
        return a;
    }

    @Override
    public boolean hasCocoon() {
        if (stacks[0] != null) {
            if (stacks[0].itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (stacks[0].getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()) {
                    return true;
                }
            }
        }
        return false;
    }
}
