package net.minecraft.src.denoflionsx.plugins.BluesFood;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.API.OvenRecipeManager;
import net.minecraft.src.denoflionsx.API.OvenRecipeManager.OvenRecipe;
import net.minecraft.src.denoflionsx.MachineTemplate.*;
import net.minecraft.src.denoflionsx.core.core;

public class MachineOven {

    public static final String gui = "/denoflionsx/oven_food_gui.png";
    public BlockOven oven = new BlockOven(181, "Blue Oven");

    public MachineOven() {
    }

    public static class BlockOven extends baseBlock {

        public BlockOven(int par1, String name) {
            super(par1, name);
        }

        @Override
        public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5) {
            super.onBlockPlaced(par1World, par2, par3, par4, par5);
            tile = new TileEntityOven(12);
        }
    }

    public static class TileEntityOven extends baseTileEntity {

        public static final int slotnum = 12;
        public static final int CookTime = (5 * 20);
        public static final boolean debug = true;
        private ItemStack output = null;
        private boolean hasRecipe = false;
        private int burntime;
        private boolean inventoryChangedSinceLastCheck = false;
        private int progress;

        static {
            ItemStack n = null;
            OvenRecipeManager.addRecipe(new ItemStack(Item.cake), new ItemStack(Item.ingotIron), new ItemStack[]{new ItemStack(Item.bucketMilk), n, n,
                        n, n, n,
                        n, n, n});
        }

        public TileEntityOven(int slots) {
            super(slots);
        }

        public TileEntityOven() {
            this.inventory = new ItemStack[slotnum];
            onInventoryChanged();
        }

        public ItemStack[] getCraftingGrid() {
            ItemStack[] grid = new ItemStack[9];
            int i = 0;
            for (ItemStack a : this.inventory) {
                if (i < 8) {
                    if (a != null) {
                        grid[i] = a;
                    } else {
                        ItemStack n = null;
                        grid[i] = n;
                    }
                }
                i++;
            }

            if (debug) {
                // What is in the crafting grid?
                core.print("--------------------------------------");
                core.print(grid[0] + " " + grid[1] + " " + grid[2]);
                core.print(grid[3] + " " + grid[4] + " " + grid[5]);
                core.print(grid[6] + " " + grid[7] + " " + grid[8]);
            }
            return grid;
        }

        @Override
        public void onInventoryChanged() {
            ItemStack[] grid;
            grid = getCraftingGrid();
            OvenRecipe r = OvenRecipeManager.findMatchingRecipe(this.getTool(), grid);
            if (r == null) {
                this.hasRecipe = false;
            } else {
                this.output = r.getOutput();
                this.hasRecipe = true;
            }
            this.inventoryChangedSinceLastCheck = true;
        }

        public static int getBurnTime(ItemStack i) {
            if (i != null) {
                return FMLCommonHandler.instance().fuelLookup(i.getItem().shiftedIndex, i.getItemDamageForDisplay());
            } else {
                return 0;
            }
        }

        public boolean hasFuel() {
            if (getBurnTime(this.inventory[10]) != 0) {
                return true;
            } else {
                return false;
            }
        }

        public ItemStack getTool() {
            return this.inventory[9];
        }

        public boolean hasTool() {
            if (this.inventory[9] != null) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void updateEntity() {
            if (this.inventoryChangedSinceLastCheck) {
                this.progress = 0;
                this.inventoryChangedSinceLastCheck = false;
            }
            if (this.burntime == 0 && this.output != null && this.hasRecipe) {
                if (this.hasFuel() && this.hasTool()) {
                    this.burntime = getBurnTime(this.inventory[10]);
                    this.inventory[10].stackSize--;
                }
            } else if (this.burntime > 0 && this.hasRecipe) {
                this.progress++;
                if (this.progress >= CookTime) {
                    for (int i = 0; i != 8; i++) {
                        if (this.inventory[i] != null) {
                            if (this.inventory[i].stackSize == 1) {
                                this.inventory[i].stackSize = 0;
                            } else {
                                this.inventory[i].stackSize--;
                            }
                        }
                    }
                    this.inventory[11] = this.output.copy();
                } else {
                    this.progress++;
                }
                this.burntime--;
            }


        }

        @Override
        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            this.burntime = tagCompound.getInteger("BurnTime");
        }

        @Override
        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            tagCompound.setInteger("BurnTime", this.burntime);
        }
    }

    public static class GUIOven extends baseGUI {

        public static final ArrayList<CoordObject> map = new ArrayList();

        static {
            map.add(new CoordObject(0, 17, 17));
            map.add(new CoordObject(1, 35, 17));
            map.add(new CoordObject(2, 53, 17));
            map.add(new CoordObject(3, 17, 35));
            map.add(new CoordObject(4, 35, 35));
            map.add(new CoordObject(5, 53, 35));
            map.add(new CoordObject(6, 17, 53));
            map.add(new CoordObject(7, 35, 53));
            map.add(new CoordObject(8, 53, 53));
            map.add(new CoordObject(9, 75, 17));
            map.add(new CoordObject(10, 75, 53));
            map.add(new CoordObject(11, 138, 35));
        }

        public GUIOven(EntityPlayer player, baseTileEntity tile) {
            super(player, tile, map);
        }

        @Override
        protected void drawGuiContainerForegroundLayer() {
            this.title("Blue Oven");
        }

        @Override
        public String getTexture() {
            return gui;
        }
    }
}
