package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.plugins.BlueSilkWorm.Gadget.GadgetIncubator;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormMothAccess;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import net.minecraft.src.TileEntity;

public class TriggerHasMoth extends PfFCustomTrigger{

    public TriggerHasMoth(int id) {
        super(id);
    }

    @Override
    public String getDescription() {
        return "Has Moth";
    }

    @Override
    public int getIndexInTexture() {
        return SilkWormGrowthStages.MOTH.getTexture();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof PfFMachineTileEntity){
            PfFMachineTileEntity moth = (PfFMachineTileEntity)tile;
            if (moth.gadget instanceof GadgetIncubator){
                GadgetIncubator gadget = (GadgetIncubator) moth.gadget;
                if (gadget instanceof ISilkWormMothAccess){
                    return gadget.hasMoth(moth);
                }
            }
            
        }
        return false;
    }
    
    
  
}
