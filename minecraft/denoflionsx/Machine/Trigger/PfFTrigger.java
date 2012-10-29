package denoflionsx.Machine.Trigger;

import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;
import denoflionsx.PluginsforForestry;
import net.minecraft.src.TileEntity;

public class PfFTrigger extends Trigger{

    public PfFTrigger(int id) {
        super(id);
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getIndexInTexture() {
        return 0;
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        return false;
    }
    
    
    
    
}
