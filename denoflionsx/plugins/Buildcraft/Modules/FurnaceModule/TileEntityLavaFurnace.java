package denoflionsx.plugins.Buildcraft.Modules.FurnaceModule;

import buildcraft.api.core.Orientations;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidStack;
import buildcraft.api.liquids.LiquidTank;
import denoflionsx.core.core;
import net.minecraft.src.*;

public class TileEntityLavaFurnace extends TileEntityFurnace implements ITankContainer {

    public final ILiquidTank tank = new LiquidTank(1000);
    public final int LiquidID = Block.lavaMoving.blockID;
    public boolean hasLiquid = false;

    public TileEntityLavaFurnace() {
    }

    @Override
    public LiquidStack drain(Orientations from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public void updateEntity() {
        if (this.furnaceBurnTime <= 0) {
            if (this.tank.getLiquid() != null) {
                if (!this.hasLiquid) {
                    core.print("Liquid Detected!");
                    this.hasLiquid = true;
                }
                if (this.tank.getLiquid().amount >= 1000) {
                    this.tank.getLiquid().amount = -1000;
                    this.furnaceBurnTime += 20000;
                    BlockLavaFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }
            }
        }
        super.updateEntity();
    }

    @Override
    public int fill(Orientations from, LiquidStack resource, boolean doFill) {
        if (resource.asItemStack().isItemEqual(new ItemStack(Block.lavaStill)) && this.furnaceBurnTime <= 0) {
            return tank.fill(resource, doFill);
        } else {
            return 0;
        }
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        if (resource.asItemStack().isItemEqual(new ItemStack(Block.lavaStill)) && this.furnaceBurnTime <= 0) {
            return tank.fill(resource, doFill);
        } else {
            return 0;
        }
    }

    @Override
    public ILiquidTank[] getTanks() {
        return new ILiquidTank[]{this.tank};
    }
}
