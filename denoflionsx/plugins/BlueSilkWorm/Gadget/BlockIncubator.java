package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.PfFMachineBlock;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockIncubator extends PfFMachineBlock{

    public BlockIncubator(int par1, Material par3Material, IPfFGadget gadget, String name) {
        super(par1, par3Material, gadget, name);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityIncubator(this.gadget);
    }

}
