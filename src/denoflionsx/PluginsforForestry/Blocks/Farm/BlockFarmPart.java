package denoflionsx.PluginsforForestry.Blocks.Farm;

import denoflionsx.PluginsforForestry.PfF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFarmPart extends BlockContainer {

    private HashMap<Integer, Class> TEMap = new HashMap();
    private HashMap<Integer, Integer> textures = new HashMap();
    private ArrayList<ItemStack> stacks = new ArrayList();

    public BlockFarmPart(int par1, Material par2Material) {
        super(par1, par2Material);
        try {
            add(Class.forName("denoflionsx.PluginsforForestry.Blocks.Farm.TileEntityFarmPart"), 0, 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setHardness(0.3f);
        this.setCreativeTab(PfF.Core.tab);
        this.setTextureFile(PfF.Core.spritesheet);
    }

    private void add(Class TE, int meta, int texture) {
        TEMap.put(meta, TE);
        textures.put(meta, texture);
        ItemStack i = new ItemStack(this, 1, meta);
        stacks.add(i);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int meta) {
        try {
            return (TileEntity) TEMap.get(meta).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return null;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return textures.get(par2);
    }

    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (ItemStack i : stacks) {
            par3List.add(i);
        }
    }
}
