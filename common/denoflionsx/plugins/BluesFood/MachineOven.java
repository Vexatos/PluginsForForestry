package denoflionsx.plugins.BluesFood;

import denoflionsx.API.OvenRecipeManager;
import denoflionsx.API.OvenRecipeManager.OvenRecipe;
import java.util.ArrayList;
import net.minecraft.src.*;
import denoflionsx.MachineTemplate.*;
import denoflionsx.core.core;
import denoflionsx.mod_PluginsforForestry;

public class MachineOven {

    public static final String gui = "/denoflionsx/oven_food_gui.png";
    public BlockOven oven = new BlockOven(3333, "Blue Oven");
    public static final int gui_id = 0;

    public MachineOven() {
    }

    public static class BlockOven extends baseBlock {

        public BlockOven(int par1, String name) {
            super(par1, name);
        }

        @Override
        public TileEntity createNewTileEntity(World var1) {
            return this.tile = new TileEntityOven(12);
        }

        @Override
        public TileEntity createNewTileEntity(World world, int metadata) {
            return this.tile = new TileEntityOven(12);
        }

        @Override
        public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
            par5EntityPlayer.openGui(mod_PluginsforForestry.instance, gui_id, par1World, par2, par3, par4);
            return true;
        }
    }

    public static class TileEntityOven extends baseTileEntity {

        public static final int slotnum = 12;
        public static final boolean debug = false;
        private int cookTime;
        private ItemStack output = null;
        private boolean hasRecipe = false;
        private int burntime;
        private boolean inventoryChangedSinceLastCheck = false;
        private int progress;

        static {
            ArrayList<ItemStack> test = new ArrayList();
            test.add(new ItemStack(Item.appleGold));
            for (int i = 0; i > 8; i++){
                test.add(null);
            }
            OvenRecipeManager.addRecipe(new ItemStack(Item.appleGold), test);
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
            this.inventoryChangedSinceLastCheck = true;
            this.progress = 0;
            if (this.hasRecipe = OvenRecipeManager.isRecipe(this.getCraftingGrid())){
                OvenRecipe r = OvenRecipeManager.getRecipeResult(this.getCraftingGrid());
                this.output = r.getOutput();
                this.cookTime = r.getCookTime();
            }
        }

        public static int getBurnTime(ItemStack i) {
            if (i != null) {
                return TileEntityFurnace.getItemBurnTime(i);
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

        public ItemStack getFuel() {
            return this.inventory[10];
        }

        public void useFuel() {
            this.burntime = this.getBurnTime(this.getFuel());
            this.getFuel().stackSize--;
        }

        public ItemStack getTool() {
            return this.inventory[9];
        }

        public boolean hasTool() {
            if (this.getTool() != null) {
                return true;
            } else {
                return false;
            }
        }

        public void setOutput(ItemStack item) {
            this.inventory[11] = item.copy();
        }

        @Override
        public void updateEntity() {
            if (this.burntime == 0 && this.hasRecipe) {
                if (this.hasFuel()) {
                    this.useFuel();
                }
            } else {
                if (this.hasRecipe){
                    this.progress++;
                    if (this.progress >= this.cookTime){
                        for (int i = 0; i > 8; i++){
                            if (this.inventory[i] != null){
                                this.decrStackSize(i, 1);
                            }
                        }
                        this.setOutput(this.output);
                    }
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
            baseGUIHandler.slotMap.put(gui_id, map);
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
