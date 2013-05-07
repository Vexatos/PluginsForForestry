package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class ItemWoodenBucket extends ItemLRBucket{

    public ItemWoodenBucket(int par1, int par2, String local) {
        super(par1, par2, local);
        this.setContainerItem(LRItems.itemWoodenBucketEmpty);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        ItemStack i = super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        if (i.isItemEqual(LiquidContainerRegistry.EMPTY_BUCKET)) {
            i = LRItems.ItemStackWoodenBucketEmpty.copy();
        }
        return i;
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return LRItems.ItemStackWoodenBucketEmpty.getIconIndex();
    }

}
