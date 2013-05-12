package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import cpw.mods.fml.common.Loader;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Proxy.PfFProxyClient;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class ItemWoodenBucketEmpty extends ItemLRBucket implements IRegisterRecipe {

    private Icon[] icons;

    public ItemWoodenBucketEmpty(int par1, int par2, String local) {
        super(par1, par2, local);
        this.local = PfF.Proxy.translate(local);
    }

    @Override
    public void registerRecipe() {
        PfF.Proxy.registerRecipe(new ItemStack(this), new Object[]{"XXX", "LXL", "XLX", Character.valueOf('L'), new ItemStack(Block.wood, 1, 2)});
        //----
        if (Loader.isModLoaded("Forestry")) {
            ItemStack bog = Forestry.block("soil");
            bog.setItemDamage(1);
            bog.stackSize = 6;
            ItemStack water = LiquidContainerRegistry.fillLiquidContainer(LiquidDictionary.getLiquid("Water", LiquidContainerRegistry.BUCKET_VOLUME), LRItems.ItemStackWoodenBucketEmpty);
            PfF.Proxy.registerRecipe(bog, new Object[]{"DSD", "SBS", "DSD", Character.valueOf('D'), Block.dirt, Character.valueOf('S'), Block.sand, Character.valueOf('B'), water});
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_birch");
        Icon oak = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_oak");
        Icon jungle = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_jungle");
        Icon pine = PfFProxyClient.registerIcon(par1IconRegister, "PluginsforForestry:bucket_wood_spruce");
        Icon birch = this.itemIcon;
        icons = new Icon[]{oak, pine, birch, jungle};
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return this.itemIcon;
    }

    @Override
    public Icon getIcon(ItemStack stack, int pass) {
        if (stack.stackTagCompound != null) {
            if (stack.stackTagCompound.hasKey("renderoverride")) {
                NBTTagCompound r = stack.stackTagCompound.getCompoundTag("renderoverride");
                int id = r.getInteger("wood");
                return icons[id];
            }
        }
        return this.itemIcon;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        boolean flag = this.isFull == 0;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);
        if (movingobjectposition == null) {
            return par1ItemStack;
        } else {
            FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
            if (!PluginLR.onWoodenBucket(event)){
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
