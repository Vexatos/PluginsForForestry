package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Trigger.PfFTrigger;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import net.minecraft.src.TileEntity;

public class hasCocoon extends PfFTrigger{

    public hasCocoon(int id) {
        super(id);
    }

    public hasCocoon() {
        super(902);
    }

    @Override
    public String getDescription() {
        return "Has Cocoon";
    }

    @Override
    public int getIndexInTexture() {
        return PfFManagers.ItemManager.getItem("silkcocoon").getIconIndex();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof ISilkWormCocoonAccess){
            ISilkWormCocoonAccess a = (ISilkWormCocoonAccess) tile;
            return a.hasCocoon();
        }
        return false;
    }
    
    
    
}
