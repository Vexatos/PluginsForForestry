package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.Machine.Trigger.PfFTrigger;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormFoodAccess;
import net.minecraft.src.TileEntity;

public class needsFood extends PfFTrigger {

    public needsFood(int id) {
        super(id);
    }

    public needsFood() {
        super(900);
    }

    @Override
    public String getDescription() {
        return "Needs Food";
    }

    @Override
    public int getIndexInTexture() {
        return 1;
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof ISilkWormFoodAccess) {
            ISilkWormFoodAccess f = (ISilkWormFoodAccess) tile;
            if (!f.hasFood()){
                return true;
            }
        }
        return false;
    }
}
