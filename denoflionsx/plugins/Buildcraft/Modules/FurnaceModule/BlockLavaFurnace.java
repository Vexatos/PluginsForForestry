package denoflionsx.plugins.Buildcraft.Modules.FurnaceModule;

import net.minecraft.src.BlockFurnace;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockLavaFurnace extends BlockFurnace{

    public BlockLavaFurnace(int par1, boolean par2) {
        super(par1, par2);
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityLavaFurnace();
    } 
}
