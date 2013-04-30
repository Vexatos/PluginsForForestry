package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class ItemLRBucket extends ItemBucket {

    private int _liquidId;
    public String local;

    public ItemLRBucket(int par1, int par2, String local) {
        super(par1, par2);
        _liquidId = par2;
        this.setContainerItem(LiquidContainerRegistry.EMPTY_BUCKET.getItem());
        this.setMaxStackSize(1);
        this.local = local;
    }

    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public boolean tryPlaceContainedLiquid(World world, double xOffset, double yOffset, double zOffset, int x, int y, int z) {
        if (_liquidId > 4096) {
            return false;
        }
        if (_liquidId <= 0) {
            return false;
        } else if (!world.isAirBlock(x, y, z) && world.getBlockMaterial(x, y, z).isSolid()) {
            return false;
        } else {
            world.setBlock(x, y, z, _liquidId, 7, 3);
            return true;
        }
    }

    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes) {
        subTypes.add(new ItemStack(itemId, 1, 0));
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        return local;
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    }

    @Override
    public Icon getIconFromDamage(int par1) {
         return LiquidContainerRegistry.EMPTY_BUCKET.getIconIndex();
    }
    
    
}
