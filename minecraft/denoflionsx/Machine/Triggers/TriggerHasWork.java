package denoflionsx.Machine.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import net.minecraft.src.TileEntity;

public class TriggerHasWork extends PfFCustomTrigger{

    public TriggerHasWork(int id) {
        super(id);
    }

    @Override
    public String getDescription() {
        return "Has Work";
    }

    @Override
    public int getIndexInTexture() {
        return 0;
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof IHasWork){
            IHasWork i = (IHasWork)tile;
            return i.doesHaveWork();
        }
        return false;
    }
}
