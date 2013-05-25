package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.API.PfFAPI;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemWoodenBucket extends ItemLRBucket {

    public ItemWoodenBucket(int par1, int par2, String local) {
        super(par1, par2, local);
        this.setContainerItem(LRItems.itemWoodenBucketEmpty);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    }

    /*
     * This is mostly vanilla code. I tried to clean it up a bit and remove
     * shit that either doesn't actually work (cow handler) or isn't applicable
     * to this bucket.
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double) 1.0F;
        double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double) 1.0F + 1.62D - (double) par3EntityPlayer.yOffset;
        double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double) 1.0F;
        // Access Transformer makes isFull public so we can use it here.
        boolean flag = this.isFull == 0;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        int i = movingobjectposition.blockX;
        int j = movingobjectposition.blockY;
        int k = movingobjectposition.blockZ;
        if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
            if (this.isFull < 0) {
                return LRItems.ItemStackWoodenBucketEmpty.copy();
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
            if (this.tryPlaceContainedLiquid(par2World, d0, d1, d2, i, j, k) && !par3EntityPlayer.capabilities.isCreativeMode) {
                return LRItems.ItemStackWoodenBucketEmpty.copy();
            }
        }
        return par1ItemStack;
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return LRItems.ItemStackWoodenBucketEmpty.getIconIndex();
    }
    
    @Override
    public CreativeTabs getCreativeTab() {
        return PfFAPI.tab;
    }
}
