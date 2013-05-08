package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.API.Recipe.IRegisterRecipe;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Proxy.PfFProxyClient;
import forestry.api.core.BlockInterface;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class ItemWoodenBucketEmpty extends ItemLRBucket implements IRegisterRecipe {

    private Icon[] icons;

    public ItemWoodenBucketEmpty(int par1, int par2, String local) {
        super(par1, par2, local);
        this.local = PfFTranslator.instance.translateKey(local);
    }

    @Override
    public void registerRecipe() {
        PfF.Proxy.registerRecipe(new ItemStack(this), new Object[]{"XXX", "LXL", "XLX", Character.valueOf('L'), new ItemStack(Block.wood, 1, 2)});
        //----
        ItemStack bog = BlockInterface.getBlock("soil");
        bog.setItemDamage(1);
        bog.stackSize = 6;
        ItemStack water = LiquidContainerRegistry.fillLiquidContainer(LiquidDictionary.getLiquid("Water", LiquidContainerRegistry.BUCKET_VOLUME), LRItems.ItemStackWoodenBucketEmpty);
        PfF.Proxy.registerRecipe(bog, new Object[]{"DSD", "SBS", "DSD", Character.valueOf('D'), Block.dirt, Character.valueOf('S'), Block.sand, Character.valueOf('B'), water});
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
}
