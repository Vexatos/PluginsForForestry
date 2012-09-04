package denoflionsx.MachineTemplate;

import java.util.ArrayList;
import net.minecraft.src.*;

public class baseContainer extends Container {

    protected baseTileEntity t;

    public baseContainer(InventoryPlayer player, baseTileEntity t, ArrayList<CoordObject> map) {
        this.t = t;
        for (CoordObject c : map) {
            addSlotToContainer(new Slot(t, c.getX(), c.getY(), c.getZ()));
        }
        this.bindPlayerInventory(player);
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return t.isUseableByPlayer(var1);
    }

    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory) {
        super.onCraftMatrixChanged(par1IInventory);
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

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
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
}
