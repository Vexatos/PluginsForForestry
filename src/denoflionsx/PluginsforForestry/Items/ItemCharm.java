package denoflionsx.PluginsforForestry.Items;

import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.denLib.FMLWrapper;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCharm extends PfFBase {

    public ItemCharm(int par1) {
        super(par1);
        this.add("Flight Charm", 0, 20);
    }

    public ItemCharm() {
        this(CoreTuning.Items.charm);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Right click to fly");
        par3List.add("Break a block with the charm to stop flying");
    }
 
    public ItemCharm recipe() {
        if (CoreTuning.Enables.charm_crafting) {
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"FDF", "DED", "FDF", Character.valueOf('F'), new ItemStack(Item.feather), Character.valueOf('D'), new ItemStack(Item.diamond), Character.valueOf('E'), new ItemStack(Item.enderPearl)});
        }
        return this;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        par2EntityPlayer.capabilities.allowFlying = true;
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
        player.capabilities.allowFlying = false;
        return super.onBlockStartBreak(itemstack, X, Y, Z, player);
    }
}
