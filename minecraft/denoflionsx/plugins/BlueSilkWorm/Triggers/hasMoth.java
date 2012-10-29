package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Trigger.PfFTrigger;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormMothAccess;
import net.minecraft.src.TileEntity;

public class hasMoth extends PfFTrigger{

    public hasMoth(int id) {
        super(id);
    }

    public hasMoth() {
        super(903);
    }

    @Override
    public String getDescription() {
        return "Has Moth";
    }

    @Override
    public int getIndexInTexture() {
        return PfFManagers.ItemManager.getItem("silkmoth").getIconIndex();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof ISilkWormMothAccess){
            ISilkWormMothAccess a = (ISilkWormMothAccess) tile;
            return a.hasMoth();
        }
        return false;
    }
    
    
    
}
