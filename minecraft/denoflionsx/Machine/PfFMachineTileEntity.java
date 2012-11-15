package denoflionsx.Machine;

import buildcraft.api.gates.ITrigger;
import denoflionsx.Machine.Trigger.IPfFTriggers;
import java.util.ArrayList;
import java.util.LinkedList;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.liquids.LiquidTank;

public abstract class PfFMachineTileEntity extends TileEntity implements ISidedInventory, IPfFTriggers {

    public ItemStack[] stacks = null;
    public LiquidTank[] tanks = null;
    public boolean hasWork = false;
    public boolean hasTanks = false;
    public NBTTagCompound additionalData = new NBTTagCompound();
    public ForgeSideInventory SideData = new ForgeSideInventory();
    public ArrayList<PfFSlot> slots = new ArrayList();
    public LinkedList<ITrigger> triggers = new LinkedList();

    public PfFMachineTileEntity(int size, int tanks, ITrigger[] triggers) {
        this.stacks = new ItemStack[size];
        this.tanks = new LiquidTank[tanks];
        for (int i = 0; i < tanks; i++) {
            this.tanks[i] = new LiquidTank(10000);
        }
        if (triggers != null && triggers.length != 0){
            for (ITrigger t : triggers){
                this.triggers.add(t);
            }
        }
    }

    @Override
    public LinkedList<ITrigger> getCustomTriggers() {
        return triggers;
    }

    public void MoveItemStack(int origin, int dest) {
        this.setInventorySlotContents(dest, this.getStackInSlot(origin));
        this.setInventorySlotContents(origin, null);
    }

    public void ConsumeItem(int slot) {
        this.decrStackSize(slot, 1);
    }

    public void addSlot(int index, int x, int y) {
        this.slots.add(new PfFSlot(index, x, y));
    }

    public PfFMachineTileEntity() {
        this(1, 1, null);
    }

    public abstract String getName();

    public abstract String getGUITexture();

    @Override
    public int getSizeInventorySide(ForgeDirection side) {
        if (SideData.Size.get(side) == null) {
            return 0;
        }
        return SideData.Size.get(side);
    }

    @Override
    public int getStartInventorySide(ForgeDirection side) {
        if (SideData.Start.get(side) == null) {
            return 0;
        }
        return SideData.Start.get(side);
    }

    @Override
    public void closeChest() {
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amt) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public String getInvName() {
        return "PfF." + "GenericMachine";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return stacks[var1];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        stacks[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    public void attachToNBT(NBTTagCompound tagCompound) {
        NBTTagList itemList;
        itemList = new NBTTagList();
        for (int i = 0; i < stacks.length; i++) {
            ItemStack stack = stacks[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        this.additionalData.setTag("Inventory", itemList);
        tagCompound.setTag("dolx", this.additionalData);
    }

    public void syncFromNBT(NBTTagCompound tagCompound) {
        this.additionalData = tagCompound.getCompoundTag("dolx");
        NBTTagList tagList;
        tagList = this.additionalData.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < stacks.length) {
                stacks[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.syncFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        this.attachToNBT(tagCompound);
    }
}
