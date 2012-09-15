package denoflionsx.plugins.FarmCraftory;

import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops.TreeObject;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropTreeProviderDummy implements ICropProvider{
    
    private TreeObject tree;

    public cropTreeProviderDummy(TreeObject t) {
        this.tree = t;
    }
    
    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        return false;
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropHarvestTree(world,x,y,z);
    }

    @Override
    public ItemStack[] getWindfall() {
        return this.tree.asArray();
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
//        if (world.getBlockId(x, y, z) != 0){
//           core.print("Looking for " + this.tree.getFruitItem().getItem().getItemNameIS(this.tree.getFruitItem()) + ". Found " + Block.blocksList[world.getBlockId(x, y, z)].getBlockName()); 
//        }
        
        return this.tree.isFruit(world.getBlockId(x,y,z));
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        return false;
    }
}
