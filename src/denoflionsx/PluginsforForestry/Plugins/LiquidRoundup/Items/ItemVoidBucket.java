package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class ItemVoidBucket extends ItemMeta {

    public ItemVoidBucket(String[] textures, int par1) {
        super(textures, par1);
        this.createItemEntry(0, "Void Bucket");
        this.createItemEntry(1, "Void Bucket (3x3)");
        this.createItemEntry(2, "Void Bucket (6x6)");
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return super.getIconFromDamage(0);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        boolean flag = true;
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

    public static enum VoidLevels {

        voidbucket(1),
        level2voidbucket(3),
        level3voidbucket(6);
        private int area;

        private VoidLevels(int area) {
            this.area = area;
        }

        public int getArea() {
            return area;
        }
    }
}
