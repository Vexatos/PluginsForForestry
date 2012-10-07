package denoflionsx.Machine;

import denoflionsx.API.PfFManagers;
import denoflionsx.Interfaces.IPfFTrigger;
import denoflionsx.core.core;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.BlueSilkWorm.Triggers.Triggers;
import java.util.LinkedList;
import net.minecraft.src.*;

public class PfFMachineTileEntity extends TileEntity implements IInventory, ISilkWormCocoonAccess, IPfFTrigger {

    public static final String texture = "/denoflionsx/barrel_ahmg.png";
    private ItemStack[] stacks = new ItemStack[1];
    public String id = "Life Span";
    public String time = "Life Points";
    public boolean stopCocoon = false;

    public PfFMachineTileEntity() {
    }

    @Override
    public LinkedList getCustomTriggers() {
        LinkedList a = new LinkedList();
        a.add(Triggers.hasCocoon);
        return a;
    }

    @Override
    public boolean hasCocoon() {
        if (stacks[0] != null){
            if (stacks[0].itemID == PfFManagers.ItemManager.getItem("silkworm").itemID){
                if (stacks[0].getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateEntity() {
        if (this.getStackInSlot(0) != null) {
            if (stacks[0].itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                if (SilkWormHelper.isWormValid(stacks[0])) {
                    id = SilkWormHelper.getWormLifeSpanLabel(stacks[0]);
                    time = String.valueOf(SilkWormHelper.getWormLifeSpanInt(stacks[0]));
                    if (stopCocoon && stacks[0].getItemDamage() == SilkWormGrowthStages.COCOON.getMeta()) {
                        core.print("Stop!");
                    }else{
                        SilkWormHelper.progressWorm(stacks[0]);
                    }
                } else {
                    SilkWormHelper.setupWorm(stacks[0]);
                }
            }
        }
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
        return "PfF.Machine";
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
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
    }
}
