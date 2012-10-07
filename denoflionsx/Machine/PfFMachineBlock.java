package denoflionsx.Machine;

import denoflionsx.PluginsforForestry;
import net.minecraft.src.*;

public class PfFMachineBlock extends BlockContainer{

    public PfFMachineBlock(int par1, int par2, Material par3Material) {
        super(par1, par2, par3Material);
        this.setBlockName("pffmachineblock");
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new PfFMachineTileEntity();
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        par5EntityPlayer.openGui(PluginsforForestry.instance, 0, par1World, par2, par3, par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }
    

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

}
