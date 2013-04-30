package denoflionsx.PluginsforForestry.Plugins.OmniPlant.Blocks;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockTEPlant extends BlockContainer {

    public BlockTEPlant(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setTickRandomly(true);
        this.setBlockBounds(0.5F - 0.5F, 0.0F, 0.5F - 0.5F, 0.5F + 0.5F, 0.25F, 0.5F + 0.5F);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        return super.getIcon(par1, par2);
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
    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    @Override
    public int idPicked(World par1World, int par2, int par3, int par4) {
        return 0;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }
    
    
}
