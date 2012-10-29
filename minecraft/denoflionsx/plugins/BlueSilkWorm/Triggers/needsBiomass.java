package denoflionsx.plugins.BlueSilkWorm.Triggers;

import buildcraft.api.gates.ITriggerParameter;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.Machine.Trigger.ILiquidContainerAccess;
import denoflionsx.Machine.Trigger.PfFTrigger;
import net.minecraft.src.TileEntity;

public class needsBiomass extends PfFTrigger{

    public needsBiomass(int id) {
        super(id);
    }

    public needsBiomass() {
        super(904);
    }

    @Override
    public String getDescription() {
        return "Needs Biomass";
    }

    @Override
    public int getIndexInTexture() {
        return EnumForestryLiquids.BIOMASS.getLiquid().getIconIndex();
    }

    @Override
    public String getTextureFile() {
        return EnumForestryLiquids.BIOMASS.getLiquid().getItem().getTextureFile();
    }

    @Override
    public boolean isTriggerActive(TileEntity tile, ITriggerParameter parameter) {
        if (tile instanceof ILiquidContainerAccess){
            ILiquidContainerAccess a = (ILiquidContainerAccess) tile;
            return !a.hasLiquidContainer();
        }
        return false;
    }

}
