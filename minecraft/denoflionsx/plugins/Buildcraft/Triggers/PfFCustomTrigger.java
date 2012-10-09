package denoflionsx.plugins.Buildcraft.Triggers;

import buildcraft.api.gates.Trigger;
import denoflionsx.PluginsforForestry;

public class PfFCustomTrigger extends Trigger{

    public PfFCustomTrigger(int id) {
        super(id);
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }

}
