package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidStack;

public class ItemMetaBucket extends ItemMeta {

    private HashMap<Integer, LiquidStack> liquids = new HashMap();
    private ItemStack empty;

    public ItemMetaBucket(int par1, ItemStack empty) {
        super(par1);
        this.empty = empty;
        this.setMaxStackSize(1);
        this.setContainerItem(empty.getItem());
    }

    public ItemStack register(int meta, String name, LiquidStack liquid) {
        names.put(meta, name);
        liquids.put(meta, liquid);
        ItemStack s = new ItemStack(this, 1, meta);
        this.stacks.add(s);
        return s;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return this.names.get(par1ItemStack.getItemDamage());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int isFull = this.liquids.get(par1ItemStack.getItemDamage()).itemID;
        double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double) 1.0F;
        double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double) 1.0F + 1.62D - (double) par3EntityPlayer.yOffset;
        double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double) 1.0F;
        boolean flag = isFull == 0;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        int i = movingobjectposition.blockX;
        int j = movingobjectposition.blockY;
        int k = movingobjectposition.blockZ;
        if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
            if (isFull < 0) {
                return this.empty.copy();
            }
            if (movingobjectposition.sideHit == 0) {
                --j;
            }
            if (movingobjectposition.sideHit == 1) {
                ++j;
            }
            if (movingobjectposition.sideHit == 2) {
                --k;
            }
            if (movingobjectposition.sideHit == 3) {
                ++k;
            }
            if (movingobjectposition.sideHit == 4) {
                --i;
            }
            if (movingobjectposition.sideHit == 5) {
                ++i;
            }
            if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack)) {
                return par1ItemStack;
            }
            if (this.tryPlaceContainedLiquid(par2World, d0, d1, d2, i, j, k, par1ItemStack)) {
                return this.empty.copy();
            }
        }
        return par1ItemStack;
    }

    public boolean tryPlaceContainedLiquid(World world, double xOffset, double yOffset, double zOffset, int x, int y, int z, ItemStack item) {
        int _liquidId = this.liquids.get(item.getItemDamage()).itemID;
        if (_liquidId > 4096) {
            return false;
        }
        if (_liquidId <= 0) {
            return false;
        } else if (!world.isAirBlock(x, y, z) && world.getBlockMaterial(x, y, z).isSolid()) {
            return false;
        } else {
            // 7, 3
            if (_liquidId == Block.waterStill.blockID) {
                world.setBlock(x, y, z, Block.waterMoving.blockID, 0, 3);
            } else {
                world.setBlock(x, y, z, _liquidId, 7, 3);
            }
            return true;
        }
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return PfFAPI.tab;
    }

    public HashMap<Integer, String> getNames() {
        return names;
    }

    public HashMap<Integer, LiquidStack> getLiquids() {
        return liquids;
    }

    public ItemStack getEmpty() {
        return empty;
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return this.empty.getIconIndex();
    }
}
