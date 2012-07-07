package net.minecraft.src.denoflionsx.plugins.IC2;

import net.minecraft.src.Block;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.buildcraft.api.ILiquidContainer;
import net.minecraft.src.buildcraft.api.Orientations;
import net.minecraft.src.buildcraft.api.Position;
import net.minecraft.src.ic2.api.Direction;
import net.minecraft.src.ic2.api.EnergyNet;
import net.minecraft.src.ic2.api.IEnergySink;

public class TileEntityMachine extends TileEntity implements IEnergySink, ILiquidContainer {

    protected int storedeu;
    protected int maxeu;
    protected int storedliquid;
    protected int maxliquid;
    protected boolean isAdded = false;
    protected boolean valid = true;

    public TileEntityMachine(int par1) {
        this.maxeu = par1;
        this.maxliquid = par1;
    }

    public TileEntityMachine() {
    }

    public int getStoredLiquid() {
        return this.storedliquid;
    }

    public int getStoredEU() {
        return this.storedeu;
    }

    @Override
    public boolean demandsEnergy() {
        return this.storedeu < this.maxeu;
    }

    @Override
    public int injectEnergy(Direction directionFrom, int amount) {
        int amountToAdd = Math.min(amount, this.maxeu - this.storedeu);
        this.storedeu += amountToAdd;
        return amount - amountToAdd;
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction) {
        return true;
    }

    @Override
    public boolean isAddedToEnergyNet() {
        return this.isAdded;
    }

    @Override
    public void updateEntity() {
        if (!this.isAdded) {
            EnergyNet.getForWorld(worldObj).addTileEntity(this);
            this.isAdded = true;
        }
        if (this.storedeu > 0 && this.storedeu - 20 >= 0) {
            this.storedeu -= 20;
            this.storedliquid += 20;
        }
        if (this.storedliquid > 0) {
            for (int i = 0; i < 6; ++i) {
                Position p = new Position(xCoord, yCoord, zCoord, Orientations.values()[i]);
                p.moveForwards(1);
                TileEntity tile = worldObj.getBlockTileEntity((int) p.x, (int) p.y, (int) p.z);
                if (tile instanceof ILiquidContainer) {
                    int liquidToProduce = 100;
                    int liquidRemaining = liquidToProduce - ((ILiquidContainer) tile).fill(p.orientation.reverse(), liquidToProduce,this.getLiquidId(), true);
                    this.storedliquid -= (liquidToProduce - liquidRemaining);
                    if (liquidRemaining <= 0) {
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void invalidate() {
        this.valid = false;
        if (this.isAdded) {
            EnergyNet.getForWorld(worldObj).removeTileEntity(this);
            this.isAdded = false;
        }
    }

    @Override
    public void validate() {
        this.valid = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.maxeu = par1NBTTagCompound.getInteger("maxeu");
        this.storedeu = par1NBTTagCompound.getInteger("stored");
        this.storedliquid = par1NBTTagCompound.getInteger("storedliquid");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("stored", this.storedeu);
        par1NBTTagCompound.setInteger("maxeu", this.maxeu);
        par1NBTTagCompound.setInteger("storedliquid", this.storedliquid);
    }

    @Override
    public int empty(int var1, boolean var2) {
        return 0;
    }

    @Override
    public int fill(Orientations var1, int var2, int var3, boolean var4) {
        return 0;
    }

    @Override
    public int getCapacity() {
        return this.maxliquid;
    }

    @Override
    public int getLiquidId() {
        return Block.waterStill.blockID;
    }

    @Override
    public int getLiquidQuantity() {
        return this.storedliquid;
    }
}
