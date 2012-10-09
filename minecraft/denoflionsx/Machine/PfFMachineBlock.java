package denoflionsx.Machine;

import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.PluginsforForestry;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import net.minecraft.src.*;

public class PfFMachineBlock extends BlockContainer{
    
    public IPfFGadget gadget;

    public PfFMachineBlock(int par1, Material par3Material, IPfFGadget gadget, String name) {
        super(par1, par3Material);
        this.setBlockName(denLib.toLowerCaseNoSpaces(name));
        FMLWrapper.MODE.FML.registerBlock(this);
        FMLWrapper.MODE.FML.registerBlockName(this, name);
        this.gadget = gadget;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new PfFMachineTileEntity(gadget);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        par5EntityPlayer.openGui(PluginsforForestry.instance, this.gadget.getGuiID(), par1World, par2, par3, par4);
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }
    
    
}
