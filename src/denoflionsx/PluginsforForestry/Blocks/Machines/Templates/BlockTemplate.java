package denoflionsx.PluginsforForestry.Blocks.Machines.Templates;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTemplate extends BlockContainer{

    public BlockTemplate(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList s = new ArrayList();
        s.addAll(Arrays.asList(new ItemStack[]{new ItemStack(this,1,metadata)}));
        return s;
    }
    
    

}
