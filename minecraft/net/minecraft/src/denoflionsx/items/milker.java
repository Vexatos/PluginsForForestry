package net.minecraft.src.denoflionsx.items;

import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.TankManager;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;

public class milker {

    protected EntityPlayer player;
    protected TankManager tank;
    public static ItemStack[] validContainers = new ItemStack[]{ItemInterface.getItem("waxCapsule"), ItemInterface.getItem("canEmpty"), ItemInterface.getItem("refractoryEmpty"), new ItemStack(Item.bucketEmpty)};
    protected int itemId;
    protected boolean hasValidContainer = false;
    protected boolean hasInventorySpace = false;
    protected int containerid;
    protected LiquidContainer forFill;
    protected int index;

    public milker(EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6) {

        this.player = par2EntityPlayer;
        this.tank = new TankManager(par3World, par4, par5, par6);
        core.print("" + this.tank.stored);


    }

    // I am really freaking proud of this thing.
    public void milk() {
        int q = -1;
        for (ItemStack i : this.player.inventory.mainInventory) {
            q++;
            if (i == null) {
                // Check for empty bag space.
                this.hasInventorySpace = true;
                this.index = q;
            }
            for (ItemStack k : validContainers) {
                // Container targeting.
                // Search the player's bag for a liquid container that we can use.
                if (!hasValidContainer) {
                    if (i != null) {
                        if (k.getItem().shiftedIndex == i.getItem().shiftedIndex) {
                            this.hasValidContainer = true;
                            this.containerid = i.getItem().shiftedIndex;
                            break;
                        }
                    }

                }
            }
        }
        // Final checks. Do we have a liquid, a valid container, and inventory space to hold it?
        if (this.tank.liquidid != 0 && this.hasValidContainer && this.hasInventorySpace) {
            LiquidContainer lc = LiquidManager.getEmptyContainer(new ItemStack(this.containerid, 1, 0), new LiquidStack(this.tank.liquidid, 1000));
            // Do we have a matching liquid to container relationship in Forestry?
            if (lc != null) {
                // Did the tank remove liquid successfully?
                if (this.tank.empty()) {

                    // player.inventory.addItemStackToInventory(lc.filled);
                    //player.dropPlayerItemWithRandomChoice(lc.filled, false);
                    player.inventory.consumeInventoryItem(this.containerid);
                    player.inventory.setInventorySlotContents(this.index, lc.filled);
                    
                }

            }

        }
    }

    public void antimilk() {
        core.print("antimilk called!");
        for (ItemStack i : this.player.inventory.mainInventory) {
            if (i == null) {
                // Check for empty bag space.
                this.hasInventorySpace = true;
                if (this.tank.stored == 0) {
                    // No liquid in tank. Find a liquid to deposit.
                    if (i != null) {
                        LiquidContainer z = LiquidManager.getLiquidContainer(i);
                        if (z != null) {
                            if (z.filled.getItem().shiftedIndex == i.getItem().shiftedIndex && !this.hasValidContainer) {
                                core.print("Found liquid in bags!");
                                this.forFill = LiquidManager.getLiquidContainer(i);
                                this.hasValidContainer = true;
                                break;
                            }
                        }

                    }
                }
                if (this.tank.stored != 0) {
                    // Liquid in this tank. Figure out what to deposit.
                    if (i != null) {
                        LiquidContainer z = LiquidManager.getLiquidContainer(i);
                        core.print("" + z.filled.itemID + " " + i.itemID);
                        core.print("" + LiquidManager.getLiquidContainer(i).liquid.itemID + " " + this.tank.liquidid);
                        if (LiquidManager.getLiquidContainer(i).liquid.isLiquidEqual(i)) {
                            core.print("Found liquid in bags!");
                            this.forFill = LiquidManager.getLiquidContainer(i);
                            this.hasValidContainer = true;
                            break;
                        }
                    }
                }
            }
        }
        if (this.forFill != null) {
            if ((this.tank.liquidid == 0 || this.tank.liquidid == this.forFill.liquid.itemID) && this.hasValidContainer) {
                if (this.tank.fill()) {
                    this.player.inventory.consumeInventoryItem(this.forFill.filled.itemID);
                    if (this.forFill.isBucket) {
                        this.player.inventory.addItemStackToInventory(new ItemStack(Item.bucketEmpty));
                    }
                }
            }
        }
    }
}
