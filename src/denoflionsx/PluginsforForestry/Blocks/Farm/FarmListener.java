package denoflionsx.PluginsforForestry.Blocks.Farm;

import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.PfF;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmListener;
import forestry.api.farming.IFarmLogic;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class FarmListener implements IFarmListener {

    private LiquidTank[] tanks;
    private LiquidStack product;

    public FarmListener(LiquidTank[] tanks, LiquidStack product) {
        this.tanks = tanks;
        this.product = product;
        PfF.Proxy.print("FarmListener Activated!");
    }

    @Override
    public boolean beforeCropHarvest(ICrop crop) {
        PfF.Proxy.print("beforeCropHarvest");
        return true;
    }

    @Override
    public boolean beforeProduceStowing(ItemStack produce) {
        PfF.Proxy.print("beforeProduceStowing");
        return true;
    }

    @Override
    public void hasCollected(Collection<ItemStack> collected, IFarmLogic logic) {
        PfF.Proxy.print("hasCollected");
    }

    @Override
    public void hasCultivated(IFarmLogic logic, int x, int y, int z, ForgeDirection direction, int extent) {
        PfF.Proxy.print("hasCultivated");
    }

    @Override
    public void hasHarvested(Collection<ICrop> harvested, IFarmLogic logic, int x, int y, int z, ForgeDirection direction, int extent) {
        PfF.Proxy.print("hasHarvested");
        this.tanks[0].fill(StackUtils.getNewStack(product, 1000), true);
    }
}
