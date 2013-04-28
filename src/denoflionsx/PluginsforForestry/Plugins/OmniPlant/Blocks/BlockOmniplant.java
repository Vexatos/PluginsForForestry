package denoflionsx.PluginsforForestry.Plugins.OmniPlant.Blocks;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOmniplant extends BlockTEPlant {

    public BlockOmniplant(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return super.createNewTileEntity(world);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    }
}
