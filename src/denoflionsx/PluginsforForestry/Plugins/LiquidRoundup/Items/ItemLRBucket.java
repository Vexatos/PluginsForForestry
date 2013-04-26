package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.EventHandler.Credits;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class ItemLRBucket extends ItemBucket {

    private int _liquidId;

    static {
        WorldEventHandler.registerHandler(new Credits());
    }

    public ItemLRBucket(int par1, int par2, String local) {
        super(par1, par2);
        _liquidId = par2;
        this.setContainerItem(LiquidContainerRegistry.EMPTY_BUCKET.getItem());
        this.setMaxStackSize(1);
        LanguageRegistry.addName(new ItemStack(this), PfFTranslator.instance.translateKey(local));
    }
    
    public ItemStack getItemStack(){
        return new ItemStack(this);
    }

    @Override
    public boolean tryPlaceContainedLiquid(World world, double xOffset, double yOffset, double zOffset, int x, int y, int z) {
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
}
