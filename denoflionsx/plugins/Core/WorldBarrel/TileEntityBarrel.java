package denoflionsx.plugins.Core.WorldBarrel;

import buildcraft.api.core.Orientations;
import buildcraft.api.inventory.ISpecialInventory;
import denoflionsx.core.core;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityBarrel extends TileEntity implements ISpecialInventory {

    private ItemStack[] contained = new ItemStack[1];
    private ItemStack i = null;
    private int amount = 0;

    public TileEntityBarrel() {
    }

    @Override
    public void updateEntity() {
    }

    @Override
    public int addItem(ItemStack stack, boolean doAdd, Orientations from) {
        if (this.worldObj.isRemote) {
            return 0;
        }
        if (doAdd) {
            if (i == null) {
                i = stack;
                core.print("Binding barrel to " + i.getItemName());
                amount += stack.stackSize;
                return stack.stackSize;
            } else if (i != null && i.isItemEqual(stack)) {
                amount += stack.stackSize;
                core.print("Barrel has " + amount);
                return stack.stackSize;
            }
        }
        return 0;
    }

    @Override
    public ItemStack[] extractItem(boolean doRemove, Orientations from, int maxItemCount) {
        if (this.worldObj.isRemote) {
            return null;
        }
        if (amount > 0 && amount >= maxItemCount) {
            amount -= maxItemCount;
            core.print("Barrel has " + amount);
            return new ItemStack[]{new ItemStack(i.itemID, maxItemCount, i.getItemDamage())};
        } else if (amount > 0) {
            amount -= 1;
            core.print("Barrel has " + amount);
            return new ItemStack[]{new ItemStack(i.itemID, 1, i.getItemDamage())};
        }
        return null;
    }

    public void increaseAmount(int add) {
        amount += add;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public void closeChest() {
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        contained[var1].stackSize -= var2;
        return contained[var1];
    }

    @Override
    public String getInvName() {
        return "inventory.barrel";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return contained.length;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return contained[var1];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        return getStackInSlot(var1);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        contained[var1] = var2;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        amount = par1NBTTagCompound.getInteger("amount");
        int id = par1NBTTagCompound.getInteger("ItemID");
        int meta = par1NBTTagCompound.getInteger("ItemMeta");
        if (id == -1 && meta == -1) {
        } else {
            i = new ItemStack(id, 1, meta);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("amount", amount);
        if (i != null) {
            par1NBTTagCompound.setInteger("ItemID", i.itemID);
            par1NBTTagCompound.setInteger("ItemMeta", i.getItemDamage());
        } else {
            par1NBTTagCompound.setInteger("ItemID", -1);
            par1NBTTagCompound.setInteger("ItemMeta", -1);
        }
    }
}
