package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Trigger.PfFTrigger;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormAccess;
import net.minecraft.src.TileEntity;

public class needsWorm extends PfFTrigger{

    public needsWorm(int id) {
        super(id);
    }

    public needsWorm() {
        super(901);
    }
    
    @Override
    public String getDescription() {
        return "Needs Worm";
    }

    @Override
    public int getIndexInTexture() {
        return PfFManagers.ItemManager.getItem("silkworm").getIconIndex();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof ISilkWormAccess){
            ISilkWormAccess a = (ISilkWormAccess)tile;
            return !a.hasWorm();
        }
        return false;
    }
    
     
    
}
