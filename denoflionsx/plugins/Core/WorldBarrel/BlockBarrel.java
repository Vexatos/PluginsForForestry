package denoflionsx.plugins.Core.WorldBarrel;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockBarrel extends BlockContainer{

    public BlockBarrel(int par1,Material par3Material) {
        super(par1,par3Material);
        this.setBlockName("pffbarrel");
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityBarrel();
    }

}
