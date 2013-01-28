package denoflionsx.PluginsforForestry.Blocks.Machines;

import denoflionsx.PluginsforForestry.Blocks.Machines.Templates.BlockTemplate;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCore extends BlockTemplate{
    
    private static final int placeholder = 3335;

    public BlockCore(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setBlockName("PfFCoreBlock");
        this.setHardness(0.5f);
        this.setTextureFile(PfF.Core.spritesheet);
        this.setCreativeTab(PfF.Core.tab);
        this.reg();
    }

    private void reg(){
        FMLWrapper.MODE.FML.registerBlockWithItem(this, ItemBlockCore.class);
    }
    
    public BlockCore() {
        this(placeholder, Material.clay);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return super.createNewTileEntity(world, metadata);
    }
  
}
