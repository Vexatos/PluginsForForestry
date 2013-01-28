package denoflionsx.PluginsforForestry.Blocks.Plants;

import denoflionsx.PluginsforForestry.PfF;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockOmniTree extends BlockContainer implements IPlantable {

    public BlockOmniTree(int par1, Material par2Material) {
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
    public TileEntity createNewTileEntity(World var1) {
        return null;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z) {
        return this.blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z) {
        return 0;
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }
}
