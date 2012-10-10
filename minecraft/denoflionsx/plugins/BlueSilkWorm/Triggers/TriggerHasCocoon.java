package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.plugins.BlueSilkWorm.Gadget.GadgetIncubator;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import net.minecraft.src.TileEntity;

public class TriggerHasCocoon extends PfFCustomTrigger {

    public TriggerHasCocoon(int id) {
        super(id);
    }

    @Override
    public String getDescription() {
        return "Has Cocoon";
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public int getIndexInTexture() {
        return SilkWormGrowthStages.COCOON.getTexture();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof PfFMachineTileEntity) {
            PfFMachineTileEntity access = (PfFMachineTileEntity) tile;
            if (access.gadget instanceof GadgetIncubator) {
                GadgetIncubator gadget = (GadgetIncubator) access.gadget;
                if (gadget instanceof ISilkWormCocoonAccess) {
                    return gadget.hasCocoon(access);
                }
            }
        }
        return false;
    }
}
