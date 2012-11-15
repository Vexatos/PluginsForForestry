package denoflionsx.Machine;

import denoflionsx.Machine.Client.PfFSidedTexture;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.BlueSilkWorm.Helpers.EnumIncubatorSideTextures;
import java.util.Random;
import net.minecraft.src.*;

public class PfFMachineBlock extends BlockContainer {

    public static PfFSidedTexture Incubator = new PfFSidedTexture(new int[]{EnumIncubatorSideTextures.BOTTOM.getIndex(), EnumIncubatorSideTextures.TOP.getIndex(), EnumIncubatorSideTextures.SIDE.getIndex(), EnumIncubatorSideTextures.SIDE.getIndex(), EnumIncubatorSideTextures.SIDE.getIndex(), EnumIncubatorSideTextures.SIDE.getIndex()});

    public PfFMachineBlock(int par1, Material par3Material) {
        super(par1, par3Material);
        this.setBlockName("pffmachine");
        this.setHardness(0.8f);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return createNewTileEntity(var1, 0);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (metadata) {
            case 0:
                return null;
        }
        return null;
    }

    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        FMLNetworkHandler.openGui(par5EntityPlayer, PluginsforForestry.instance, par1World.getBlockMetadata(par2, par3, par4), par1World, par2, par3, par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        switch (par2) {
            case 0:
                return Incubator.getSides()[par1];
        }
        return 0;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world,
                        x + rx, y + ry, z + rz,
                        new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
}
