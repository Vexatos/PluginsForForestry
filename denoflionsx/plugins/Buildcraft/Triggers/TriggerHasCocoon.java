package denoflionsx.plugins.Buildcraft.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.Trigger;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import net.minecraft.src.TileEntity;

public class TriggerHasCocoon extends Trigger {

    public TriggerHasCocoon(int id) {
        super(id);
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
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
        if (tile instanceof ISilkWormCocoonAccess){
            ISilkWormCocoonAccess access = (ISilkWormCocoonAccess) tile;
            return access.hasCocoon();
        }
        return false;
    }
}
