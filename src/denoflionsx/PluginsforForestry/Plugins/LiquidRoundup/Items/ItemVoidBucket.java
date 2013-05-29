package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ItemVoidBucket extends ItemLRBucket {

    public ItemVoidBucket(int par1, int par2, String local) {
        super(par1, par2, local);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        boolean flag = this.isFull == 0;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
        if (movingobjectposition == null) {
            return par1ItemStack;
        } else {
            FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
            if (!PluginLR.onVoidBucket(event)) {
                return par1ItemStack;
            }
            if (event.getResult() == Event.Result.ALLOW) {
                if (par3EntityPlayer.capabilities.isCreativeMode) {
                    return par1ItemStack;
                }
                if (--par1ItemStack.stackSize <= 0) {
                    return event.result;
                }
                if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result)) {
                    par3EntityPlayer.dropPlayerItem(event.result);
                }
                return par1ItemStack;
            }
        }
        return par1ItemStack;
    }
}
