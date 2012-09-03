package net.minecraft.src.denoflionsx.MachineTemplate;

import cpw.mods.fml.common.registry.FMLRegistry;
import java.util.Random;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.denLib.denLib;

public class baseBlock extends BlockContainer {

    protected baseTileEntity tile;

    public baseBlock(int par1, String name) {
        super(par1, Material.clay);
        setHardness(2.0F);
        setResistance(5.0F);
        setBlockName(denLib.toLowerCaseNoSpaces(name));
        FMLRegistry.registerBlock(this);
        ModLoader.addName(this, name);
    }

    @Override
    public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5) {
        super.onBlockPlaced(par1World, par2, par3, par4, par5);
    }

    @Override
    public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
        par5EntityPlayer.openGui(mod_PluginsforForestry.instance, 1, par1World, par2, par3, par4);
        return super.blockActivated(par1World, par2, par3, par4, par5EntityPlayer);
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
    public TileEntity getBlockEntity() {
        return tile;
    }

    @Override
    public TileEntity getBlockEntity(int meta) {
        return tile;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
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
