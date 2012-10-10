package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.API.PfFManagers;
import denoflionsx.Machine.Gadget.PfFGadget;
import denoflionsx.plugins.BlueSilkWorm.Growth.SilkWormGrowthStages;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWorm;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormAccess;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormCocoonAccess;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormMothAccess;
import denoflionsx.plugins.BlueSilkWorm.Managers.SilkWormManagers;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import denoflionsx.plugins.Buildcraft.Triggers.Triggers;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public class GadgetIncubator extends PfFGadget implements ISilkWormCocoonAccess, ISilkWormMothAccess, ISilkWormAccess {

    public static final GadgetIncubator incubator = new GadgetIncubator(0, "Incubator", new PfFCustomTrigger[]{Triggers.hasCocoon, Triggers.hasMoth, Triggers.hasWork});
    public static final String texture = "/denoflionsx/incubator_gui.png";
    private static final int wormSlot = 8;
    private static final int[] inputSlots = new int[]{0,5};
    private static final int cocoonSlot = 9;
    private static final int[] mothSlots = new int[]{12, 20};

    public GadgetIncubator(int GuiID, String name, PfFCustomTrigger[] triggers) {
        super(GuiID, name, texture, triggers);

        // Input
        this.registerSlot(0, 8, 17);
        this.registerSlot(1, 26, 17);
        this.registerSlot(2, 44, 17);
        this.registerSlot(3, 8, 35);
        this.registerSlot(4, 26, 35);
        this.registerSlot(5, 44, 35);

        // Food
        this.registerSlot(6, 65, 17);
        this.registerSlot(7, 65, 35);

        // Processing
        this.registerSlot(8, 86, 27);
        this.registerSlot(9, 116, 27);

        // Breeding
        this.registerSlot(10, 152, 9);
        this.registerSlot(11, 152, 27);

        // Output
        this.registerSlot(12, 8, 59);
        this.registerSlot(13, 26, 59);
        this.registerSlot(14, 44, 59);
        this.registerSlot(15, 62, 59);
        this.registerSlot(16, 80, 59);
        this.registerSlot(17, 98, 59);
        this.registerSlot(18, 116, 59);
        this.registerSlot(19, 134, 59);
        this.registerSlot(20, 152, 59);
    }

    @Override
    public void updateClientOnly(IInventory inventory) {
    }

    @Override
    public void updateCommon(IInventory inventory) {
        if (inventory.getStackInSlot(wormSlot) == null){
            int slot = findWormInInputSlot(inventory,inputSlots);
            if (slot != -1){
                ItemStack worm = inventory.getStackInSlot(slot);
                if (worm.getItem() instanceof ISilkWorm){
                    inventory.setInventorySlotContents(wormSlot, worm);
                    inventory.setInventorySlotContents(slot, null);
                }
            }
        }
        updateWorm(getWorm(inventory));
        updateCocoon(getCocoon(inventory));
        if (SilkWormManagers.Registry.isItemStackCocoon(getWorm(inventory)) && getCocoon(inventory) == null) {
            inventory.setInventorySlotContents(cocoonSlot, getWorm(inventory));
            inventory.setInventorySlotContents(wormSlot, null);
        }
        if (SilkWormManagers.Registry.isItemStackMoth(getCocoon(inventory)) && getEmptyOutputSlot(inventory, mothSlots) != -1) {
            inventory.setInventorySlotContents(getEmptyOutputSlot(inventory, mothSlots), getCocoon(inventory));
            inventory.setInventorySlotContents(cocoonSlot, null);
        }
    }
    
    private int findWormInInputSlot(IInventory inventory, int[] slots){
        for (int i = slots[0]; i != slots[1]; i++){
            if (inventory.getStackInSlot(i) != null){
                return i;
            }
        }
        return -1;
    }

    private void updateCocoon(ItemStack pworm) {
        if (pworm != null) {
            if (SilkWormManagers.Registry.isItemStackCocoon(pworm)) {
                ISilkWorm worm = (ISilkWorm) pworm.getItem();
                if (worm.isWormValid(pworm)) {
                    worm.progressWormGrowth(pworm);
                } else {
                    worm.setupWorm(pworm);
                }
            }
        }
    }

    private void updateWorm(ItemStack pworm) {
        if (pworm != null) {
            if (SilkWormManagers.Registry.isItemStackWorm(pworm)) {
                ISilkWorm worm = (ISilkWorm) pworm.getItem();
                if (worm.isWormValid(pworm)) {
                    worm.progressWormGrowth(pworm);
                } else {
                    worm.setupWorm(pworm);
                }
            }
        }
    }

    private int getEmptyOutputSlot(IInventory inventory, int[] slots) {
        for (int i = slots[0]; i != slots[1]; i++) {
            if (inventory.getStackInSlot(i) == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ItemStack getCocoon(IInventory inventory) {
        return inventory.getStackInSlot(getCocoonSlot(inventory));
    }

    @Override
    public int getCocoonSlot(IInventory inventory) {
        return cocoonSlot;
    }

    @Override
    public int getMothSlot(IInventory inventory) {
        for (int i = mothSlots[0]; i != mothSlots[1]; i++) {
            if (inventory.getStackInSlot(i) != null) {
                if (inventory.getStackInSlot(i).itemID == PfFManagers.ItemManager.getItem("silkworm").itemID) {
                    if (inventory.getStackInSlot(i).getItemDamage() == SilkWormGrowthStages.MOTH.getMeta()) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public int getWormSlot(IInventory inventory) {
        return wormSlot;
    }

    @Override
    public boolean hasCocoon(IInventory inventory) {
        return getCocoon(inventory) != null;
    }

    @Override
    public ItemStack getMoth(IInventory inventory) {
        return inventory.getStackInSlot(getMothSlot(inventory));
    }

    @Override
    public boolean hasMoth(IInventory inventory) {
        return getMoth(inventory) != null;
    }

    @Override
    public ItemStack getWorm(IInventory inventory) {
        return inventory.getStackInSlot(getWormSlot(inventory));
    }

    @Override
    public boolean hasWorm(IInventory inventory) {
        return getWorm(inventory) != null;
    }
}
