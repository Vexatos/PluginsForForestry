package denoflionsx.PluginsforForestry.Blocks.Farm;

import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.denLib;
import forestry.api.core.IStructureLogic;
import forestry.api.core.ITileStructure;
import forestry.api.farming.IFarmComponent;
import forestry.api.farming.IFarmListener;
import java.lang.reflect.Field;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityFarmPart extends TileEntity implements IFarmComponent, ITankContainer {

    private LiquidTank[] tanks = new LiquidTank[1];
    public static LiquidStack sludge = null;
    //-------------------------------
    private boolean isMaster = false;
    private int masterX = 0;
    private int masterY = 0;
    private int masterZ = -99;
    private IStructureLogic logic;

    //TILEENTITY
    public TileEntityFarmPart() {
        if (sludge == null) {
            Item s = denLib.ReflectionHelper.getItem("powercrystals.minefactoryreloaded.MineFactoryReloadedCore", "sludgeItem");
            ItemStack si = new ItemStack(s);
            sludge = new LiquidStack(si.itemID, 1000);
        }
        tanks[0] = new LiquidTank(10000);
    }

    @Override
    public void updateEntity() {
    }

    //IFARMCOMPONENT
    @Override
    public boolean hasFunction() {
        return true;
    }

    @Override
    public void registerListener(IFarmListener listener) {
    }

    @Override
    public void removeListener(IFarmListener listener) {
    }

    @Override
    public ITileStructure getCentralTE() {
        if (!isIntegratedIntoStructure()) {
            return null;
        }

        if (!isMaster) {
            TileEntity tile = worldObj.getBlockTileEntity(masterX, masterY, masterZ);
            if (tile instanceof ITileStructure) {
                ITileStructure master = (ITileStructure) worldObj.getBlockTileEntity(masterX, masterY, masterZ);
                if (master.isMaster()) {
                    return master;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return this;
        }
    }

    @Override
    public IInventory getInventory() {
        return null;
    }

    @Override
    public String getTypeUID() {
        return "farm";
    }

    @Override
    public boolean isIntegratedIntoStructure() {
        return isMaster || this.masterY >= 0;
    }

    @Override
    public boolean isMaster() {
        return isMaster;
    }

    @Override
    public void makeMaster() {
    }

    @Override
    public void onStructureReset() {
    }

    @Override
    public void setCentralTE(TileEntity tile) {
        if (this.masterX != tile.xCoord || this.masterY != tile.yCoord || this.masterZ != tile.zCoord) {
            this.masterX = tile.xCoord;
            this.masterY = tile.yCoord;
            this.masterZ = tile.zCoord;
            IFarmComponent farm = (IFarmComponent) tile;
            farm.registerListener(new FarmListener(tanks, sludge));
        }
    }

    @Override
    public void validateStructure() {
        // I have no idea what I was suppose to do here.
        // So I decided to reflect into whatever Sengir's blocks are doing.
        ITileStructure s = this.getCentralTE();
        if (logic == null) {
            Class c = denLib.ReflectionHelper.getClass("forestry.farming.gadgets.TileFarm");
            Field f = denLib.ReflectionHelper.getField("structureLogic", c);
            try {
                Object o = f.get(s);
                logic = (IStructureLogic) o;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        logic.validateStructure();
    }

    //ITANKCONTAINER
    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tanks[0].drain(maxDrain, doDrain);
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        return tanks[tankIndex].drain(maxDrain, doDrain);
    }

    @Override
    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
        return tanks[0];
    }

    @Override
    public ILiquidTank[] getTanks(ForgeDirection direction) {
        return tanks;
    }
}
