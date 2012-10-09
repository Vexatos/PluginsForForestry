package denoflionsx.Machine;

import buildcraft.api.core.Orientations;
import buildcraft.api.inventory.ISpecialInventory;
import denoflionsx.Interfaces.IPfFTrigger;
import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.Gadget.PfFGadgetManager;
import denoflionsx.Machine.Triggers.IHasWork;
import denoflionsx.denLib.denLib;
import java.util.LinkedList;
import net.minecraft.src.*;

public class PfFMachineTileEntity extends TileEntity implements ISpecialInventory,IPfFTrigger,IHasWork{

    public ItemStack[] stacks;
    public boolean hasWork = false;
    public IPfFGadget gadget;

    public PfFMachineTileEntity(IPfFGadget gadget) {
        this.gadget = gadget;
        this.stacks = new ItemStack[gadget.getInventorySize()];
    }

    public PfFMachineTileEntity() {
    }

    @Override
    public LinkedList getCustomTriggers() {
        return this.gadget.getCustomTriggers();
    }

    @Override
    public boolean doesHaveWork() {
        return this.hasWork;
    }

    @Override
    public int addItem(ItemStack stack, boolean doAdd, Orientations from) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemStack[] extractItem(boolean doRemove, Orientations from, int maxItemCount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateEntity() {
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
        return "PfF." + denLib.toNoSpaces(gadget.getName());
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return stacks.length;
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

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        gadget = PfFGadgetManager.GadgetManager.getGadgetByName(tagCompound.getString(PfFMachineVars.VAR_GADGET));
        stacks = new ItemStack[gadget.getInventorySize()];
        NBTTagList tagList;
        tagList = tagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < stacks.length) {
                stacks[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
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
        tagCompound.setTag("Inventory", itemList);
        tagCompound.setString(PfFMachineVars.VAR_GADGET, gadget.getName());
    }
}
