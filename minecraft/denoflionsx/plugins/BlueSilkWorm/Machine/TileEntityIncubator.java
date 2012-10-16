package denoflionsx.plugins.BlueSilkWorm.Machine;

import buildcraft.api.core.Orientations;
import buildcraft.api.liquids.ILiquidTank;
import buildcraft.api.liquids.ITankContainer;
import buildcraft.api.liquids.LiquidManager;
import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.EnumBlockSides;
import denoflionsx.Enums.EnumForestryLiquids;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.plugins.BlueSilkWorm.Helpers.EnumIncubatorSideTextures;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.*;
import denoflionsx.plugins.BlueSilkWorm.Managers.SilkWormManagers;
import java.util.HashMap;
import net.minecraft.src.*;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityIncubator extends PfFMachineTileEntity implements ITankContainer, ISilkWormAccess, ISilkWormCocoonAccess, ISilkWormMothAccess, ISilkWormFoodAccess {

    public static final String guitexture = "/denoflionsx/incubator_gui.png";
    public static final String name = "Incubator";
    public HashMap<EnumBlockSides, EnumIncubatorSideTextures> textureMap = new HashMap();
    public static final int[] mothSlots = new int[]{10, 18};
    public static final int foodSlot = 6;
    public static final int liquid = 7;
    public static final int[] inputSlots = new int[]{0, 5};
    public static final int delay = 100;
    private int count = 0;

    public TileEntityIncubator() {
        this(19, 1);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound send = new NBTTagCompound();
        this.attachToNBT(send);
        Packet132TileEntityData packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, send);
        return packet;
    }

    @Override
    public void onDataPacket(NetworkManager net, Packet132TileEntityData pkt) {
        this.syncFromNBT(pkt.customParam1);
    }

    @Override
    public void updateEntity() {
        count++;
        if (count >= delay) {
            this.updateInput();
            this.updateFood();
            this.updateContainerInput();
            count = 0;
        }
        this.updateWorm();
        this.updateCocoon();
        this.updateInventorySlots();
    }

    public void updateInventorySlots() {
        if (SilkWormManagers.Registry.isItemStackCocoon(this.getWorm())) {
            if (this.getCocoon() == null) {
                this.MoveItemStack(this.getWormSlot(), this.getCocoonSlot());
            }
        }
        if (SilkWormManagers.Registry.isItemStackMoth(this.getCocoon())) {
            if (this.getOpenSlot() != -1) {
                this.MoveItemStack(this.getCocoonSlot(), this.getOpenSlot());
            }
        }
    }
    


    public void updateInput() {
        for (int i = inputSlots[0]; i < inputSlots[1] + 1; i++) {
            if (this.getStackInSlot(i) != null) {
                ItemStack input = this.getStackInSlot(i);
                if (SilkWormManagers.Food.isValidFood(input) && this.getStackInSlot(foodSlot) == null) {
                    this.MoveItemStack(i, foodSlot);
                }
                if (SilkWormManagers.Registry.isItemStackWorm(input) && this.getWorm() == null) {
                    this.MoveItemStack(i, this.getWormSlot());
                }
                if (SilkWormManagers.Registry.isItemStackCocoon(input) && this.getCocoon() == null) {
                    this.MoveItemStack(i, this.getCocoonSlot());
                }
            }
        }
    }

    public void updateContainerInput() {
        if (hasContainer()) {
            LiquidStack liq = getLiquidForContainer(liquid);
            if (liq != null){
                if (canTankHoldThis(liq.amount)){
                    this.fill(Orientations.Unknown, liq, true);
                    ItemStack container = this.getStackInSlot(liquid);
                    container.stackSize--;
                }
            }
        }
    }

    public boolean hasContainer() {
        if (LiquidManager.getLiquidForFilledItem(this.getStackInSlot(liquid)) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public LiquidStack getLiquidForContainer(int slot){
        if (LiquidManager.getLiquidForFilledItem(this.getStackInSlot(liquid)) != null){
            LiquidStack liq = LiquidManager.getLiquidForFilledItem(this.getStackInSlot(liquid));
            return liq;
        }
        return null;
    }

    public int checkTank() {
        int liq;
        if (this.tanks[0].getLiquid() == null) {
            this.tanks[0].setLiquid(new LiquidStack(EnumForestryLiquids.BIOMASS.getLiquid().itemID, 0));
        }
        liq = this.tanks[0].getLiquid().amount;
        return liq;
    }

    public boolean canTankHoldThis(int amount) {
        if (amount < this.tanks[0].getCapacity()) {
            int test = checkTank();
            if (this.tanks[0].getCapacity() >= (test + amount)) {
                return true;
            }
        }
        return false;
    }

    public void updateFood() {
        if (hasFood()) {
            ItemStack food = getFood();
            if (SilkWormManagers.Food.isValidFood(food)) {
                int foodPoints = SilkWormManagers.Food.getValueForFood(food);
                if (canTankHoldThis(foodPoints)) {
                    this.fill(Orientations.Unknown, new LiquidStack(EnumForestryLiquids.BIOMASS.getLiquid().itemID, foodPoints), true);
                    food.stackSize--;
                }
            }
        }
    }

    @Override
    public ItemStack getFood() {
        if (this.getFoodSlot() != -1) {
            return this.getStackInSlot(getFoodSlot());
        } else {
            return null;
        }
    }

    @Override
    public int getFoodSlot() {
        return foodSlot;
    }

    @Override
    public boolean hasFood() {
        if (this.getFood() != null) {
            return true;
        }
        return false;
    }

    public int getOpenSlot() {
        for (int i = mothSlots[0]; i < mothSlots[1] + 1; i++) {
            if (this.getStackInSlot(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public void updateWorm() {
        if (this.tanks[0].getLiquid() == null) {
            return;
        }
        if (this.tanks[0].getLiquid().amount == 0) {
            return;
        }
        if (!SilkWormManagers.Registry.isItemStackWorm(this.getWorm())) {
            return;
        }
        ItemStack w = this.getWorm();
        ISilkWorm worm = (ISilkWorm) w.getItem();
        if (!worm.isWormValid(w)) {
            worm.setupWorm(w);
        }
        this.tanks[0].getLiquid().amount--;
        worm.progressWormGrowth(w);
    }

    public void updateCocoon() {
        if (!SilkWormManagers.Registry.isItemStackCocoon(this.getCocoon())) {
            return;
        }
        ItemStack c = this.getCocoon();
        ISilkWorm cocoon = (ISilkWorm) c.getItem();
        cocoon.progressWormGrowth(c);
    }

    @Override
    public LiquidStack drain(Orientations from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public int fill(Orientations from, LiquidStack resource, boolean doFill) {
        if (resource.isLiquidEqual(EnumForestryLiquids.BIOMASS.getLiquid())) {
            return this.tanks[0].fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
        if (resource.isLiquidEqual(EnumForestryLiquids.BIOMASS.getLiquid())) {
            return this.tanks[tankIndex].fill(resource, doFill);
        }
        return 0;
    }

    @Override
    public ILiquidTank[] getTanks() {
        return this.tanks;
    }

    public int getSlotMoth() {
        for (int i = mothSlots[0]; i < mothSlots[1]; i++) {
            if (this.getStackInSlot(i) != null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ItemStack getWorm() {
        return this.getStackInSlot(getWormSlot());
    }

    @Override
    public int getWormSlot() {
        return 8;
    }

    @Override
    public boolean hasWorm() {
        if (getWorm() != null) {
            if (SilkWormManagers.Registry.isItemStackWorm(getWorm())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getCocoon() {
        return this.getStackInSlot(getCocoonSlot());
    }

    @Override
    public int getCocoonSlot() {
        return 9;
    }

    @Override
    public boolean hasCocoon() {
        if (getCocoon() != null) {
            if (SilkWormManagers.Registry.isItemStackCocoon(getCocoon())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getMoth() {
        return this.getStackInSlot(getMothSlot());
    }

    @Override
    public int getMothSlot() {
        return this.getSlotMoth();
    }

    @Override
    public boolean hasMoth() {
        if (getMothSlot() != -1) {
            return true;
        }
        return false;
    }

    public TileEntityIncubator(int size, int tanks) {
        super(size, tanks);

        //Texture Mapping.
        textureMap.put(EnumBlockSides.TOP, EnumIncubatorSideTextures.TOP);
        textureMap.put(EnumBlockSides.BOTTOM, EnumIncubatorSideTextures.BOTTOM);
        textureMap.put(EnumBlockSides.FRONT, EnumIncubatorSideTextures.SIDE);
        textureMap.put(EnumBlockSides.BACK, EnumIncubatorSideTextures.SIDE);
        textureMap.put(EnumBlockSides.LEFT, EnumIncubatorSideTextures.SIDE);
        textureMap.put(EnumBlockSides.RIGHT, EnumIncubatorSideTextures.SIDE);

        // Slot Mapping.

        // Worm input.
        this.addSlot(0, 8, 17);
        this.addSlot(1, 26, 17);
        this.addSlot(2, 44, 17);
        this.addSlot(3, 8, 35);
        this.addSlot(4, 26, 35);
        this.addSlot(5, 44, 35);

        // Food.
        this.addSlot(6, 65, 17);

        // Liquid Container.
        this.addSlot(7, 65, 35);

        // Progress
        this.addSlot(8, 152, 9);
        this.addSlot(9, 152, 27);

        // Output
        int y = 59; // All y coords are the same on these.
        this.addSlot(10, 8, y);
        this.addSlot(11, 26, y);
        this.addSlot(12, 44, y);
        this.addSlot(13, 62, y);
        this.addSlot(14, 80, y);
        this.addSlot(15, 98, y);
        this.addSlot(16, 116, y);
        this.addSlot(17, 134, y);
        this.addSlot(18, 152, y);

        // Pipe In/Out.
        this.SideData.add(ForgeDirection.UP, inputSlots);
        this.SideData.addToAllSides(mothSlots);
    }

    @Override
    public String getGUITexture() {
        return guitexture;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void attachToNBT(NBTTagCompound tagCompound) {
        NBTTagCompound tank0 = new NBTTagCompound();
        if (this.tanks[0].getLiquid() == null) {
            this.tanks[0].setLiquid(new LiquidStack(EnumForestryLiquids.BIOMASS.getLiquid().itemID, 0));
        }
        this.tanks[0].getLiquid().writeToNBT(tank0);
        this.additionalData.setTag("tank0", tank0);
        super.attachToNBT(tagCompound);
    }

    @Override
    public void syncFromNBT(NBTTagCompound tagCompound) {
        super.syncFromNBT(tagCompound);
        if (this.tanks[0].getLiquid() == null) {
            this.tanks[0].setLiquid(new LiquidStack(EnumForestryLiquids.BIOMASS.getLiquid().itemID, 0));
        }
        NBTTagCompound tank0;
        tank0 = (NBTTagCompound) this.additionalData.getTag("tank0");
        this.tanks[0].getLiquid().readFromNBT(tank0);
    }
}
