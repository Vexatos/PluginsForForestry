package denoflionsx.Machine;

import net.minecraft.src.*;

public class PfFMachineContainer extends Container {

    public PfFMachineTileEntity tile;

    public PfFMachineContainer(TileEntity tile, EntityPlayer player) {
        this.tile = (PfFMachineTileEntity) tile;
        for (PfFSlot s : this.tile.slots){
            this.addSlotToContainer(new Slot(this.tile,s.getId(),s.getX(),s.getY()));
        }
        this.bindPlayerInventory(player.inventory);
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return this.tile.isUseableByPlayer(var1);
    }

    protected final void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
        return super.mergeItemStack(par1ItemStack, par2, par3, par4);
    }

    @Override
    public ItemStack transferStackInSlot(int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //merges the item into player inventory since its in the tileEntity
            if (slot == 0) {
                if (!mergeItemStack(stackInSlot, 1,
                        inventorySlots.size(), true)) {
                    return null;
                }
                //places it into the tileEntity is possible since its in the player inventory
            } else if (!mergeItemStack(stackInSlot, 0, 1, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }
        }

        return stack;
    }
}
