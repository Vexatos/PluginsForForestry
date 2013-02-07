package denoflionsx.PluginsforForestry.Blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class GenericInventory implements IInventory{

    private ItemStack[] stacks;

    public GenericInventory(ItemStack[] stacks) {
        this.stacks = stacks;
    }
    
    @Override
    public void closeChest() {
        
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        stacks[var1].stackSize-=var2;
        return stacks[var1];
    }

    @Override
    public String getInvName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getInventoryStackLimit() {
        return stacks.length;
    }

    @Override
    public int getSizeInventory() {
        return 64;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return stacks[var1];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        return this.getStackInSlot(var1);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    @Override
    public void onInventoryChanged() {
        
    }

    @Override
    public void openChest() {
        
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        stacks[var1] = var2;
    }
    
    
    
}
