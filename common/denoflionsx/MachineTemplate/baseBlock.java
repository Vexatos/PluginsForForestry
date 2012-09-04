package denoflionsx.MachineTemplate;

import denoflionsx.core.FMLWrapper;
import java.util.Random;
import net.minecraft.src.*;
import denoflionsx.denLib.denLib;
import denoflionsx.mod_PluginsforForestry;

public class baseBlock extends BlockContainer {

    protected baseTileEntity tile;

    public baseBlock(int par1, String name) {
        super(par1, Material.clay);
        setHardness(2.0F);
        setResistance(5.0F);
        setBlockName(denLib.toLowerCaseNoSpaces(name));
        FMLWrapper.MODE.FML.registerBlock(this);
        ModLoader.addName(this, name);
    }

    public int getID() {
        return 0;
    }

    @Override
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return getTexture();
    }

    public int getTexture() {
        return 1;
    }

    @Override
    public int getBlockTextureFromSide(int par1) {
        return getTexture();
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return getTexture();
    }

    public baseTileEntity newEntity() {
        return new baseTileEntity(1);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        par5EntityPlayer.openGui(mod_PluginsforForestry.instance,0,par1World,par2,par3,par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return newEntity();
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        onBlockRemoval(par1World,par2,par3,par4);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return newEntity();
    }

    public void onBlockRemoval(World world, int x, int y, int z) {
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
