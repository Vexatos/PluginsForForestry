package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Enums.EnumBlockSides;
import denoflionsx.Machine.Gadget.IPfFGadget;
import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.Machine.PfFMachineTileEntity;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockIncubator extends PfFMachineBlock{

    public BlockIncubator(int par1, Material par3Material, IPfFGadget gadget, String name) {
        super(par1, par3Material, gadget, name);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new PfFMachineTileEntity(this.gadget);
    }

    @Override
    public int getBlockTextureFromSide(int par1) {
        if (par1 == EnumBlockSides.TOP.getIndex()){
            return EnumIncubatorSideTextures.TOP.getIndex();
        }else if (par1 == EnumBlockSides.BOTTOM.getIndex()){
            return EnumIncubatorSideTextures.BOTTOM.getIndex();
        }
        return EnumIncubatorSideTextures.SIDE.getIndex();
    }
}
