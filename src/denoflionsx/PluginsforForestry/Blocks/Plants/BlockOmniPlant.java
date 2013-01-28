package denoflionsx.PluginsforForestry.Blocks.Plants;

import denoflionsx.PluginsforForestry.API.Objects.OmniPlantExternal;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.denLib;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockOmniPlant extends BlockContainer implements IPlantable {

    public BlockOmniPlant(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setTickRandomly(true);
        this.setBlockBounds(0.5F - 0.5F, 0.0F, 0.5F - 0.5F, 0.5F + 0.5F, 0.25F, 0.5F + 0.5F);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
        this.setRequiresSelfNotify();
        this.setTextureFile(PfF.Core.spritesheet);
    }

    @Override
    public int getPlantID(World world, int x, int y, int z) {
        return this.blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z) {
        return -1;
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityOmniPlant();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public int getRenderType() {
        return 6;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        switch (par2) {
            case 0:
                return 52;
            case 1:
                return 53;
            case 2:
                return 54;
            case 3:
                return 55;
            case 4:
                return 56;
        }
        return 0;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        TileEntityOmniPlant p = (TileEntityOmniPlant) par1World.getBlockTileEntity(par2, par3, par4);
        if (p.getGrowthStage() != TileEntityOmniPlant.getFinalStage()) {
            int roll = par1World.rand.nextInt(CoreTuning.Tuning.growthFactor);
            if (roll == 0) {
                p.incrementGrowth();
                par1World.setBlockMetadata(par2, par3, par4, p.getGrowthStage());
                par1World.markBlockForRenderUpdate(par2, par3, par4);
            }
        }
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        TileEntityOmniPlant p = (TileEntityOmniPlant) par1World.getBlockTileEntity(par2, par3, par4);
        if (p == null) {
            try {
                throw new Exception("Why is the TE null?");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (p.getGrowthStage() == TileEntityOmniPlant.getFinalStage()) {
            par1World.spawnEntityInWorld(new EntityItem(par1World, (double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, p.getProduct().copy()));
            int roll;
            ItemStack seeds = PfFManagers.Items.getItemByTag(denLib.toLowerCaseNoSpaces("OmniPlant Seed"));
            if (p.getExternalStatus()) {
                roll = par1World.rand.nextInt(5);
                for (OmniPlantExternal e : PfFManagers.OmniPlant.getExternalPlants()) {
                    if (e.getProduct().equals(p.getProduct())) {
                        seeds = e.getSeed();
                    }
                }
            } else {
                roll = par1World.rand.nextInt(2);
                seeds.setItemDamage(PfFManagers.OmniPlant.getInternalIDByPlant(p.getProduct()));
            }
            seeds.stackSize = roll;
            if (roll != 0) {
                par1World.spawnEntityInWorld(new EntityItem(par1World, (double) par2 + 0.5D, (double) par3 + 0.5D, (double) par4 + 0.5D, seeds.copy()));
            }
        }
        p.invalidate();
    }
}
