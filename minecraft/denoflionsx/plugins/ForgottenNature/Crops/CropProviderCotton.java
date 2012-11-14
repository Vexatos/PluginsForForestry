package denoflionsx.plugins.ForgottenNature.Crops;

import denoflionsx.plugins.ForgottenNature.Goods.EnumGoods;
import denoflionsx.plugins.ForgottenNature.Seeds.EnumSeeds;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class CropProviderCotton implements ICropProvider{

    public CropProviderCotton() {
    }

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int space = world.getBlockId(x, y, z);
        if (space != 0){
            return false;
        }
        int field = world.getBlockId(x, y -1, z);
        if (field != Block.tilledField.blockID){
            return false;
        }else{
            world.setBlockAndMetadataWithNotify(x, y, z, EnumCrops.hempCottonBambooCornID, EnumCrops.CROPS.COTTON.getBaseMeta());
            return true;
        }
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new CropCotton(world,x,y,z);
    }

    @Override
    public ItemStack[] getWindfall() {
        return new ItemStack[]{EnumGoods.GOOD.COTTON.getItemStack()};
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        if (world.getBlockId(x, y, z) == EnumCrops.hempCottonBambooCornID && world.getBlockMetadata(x, y, z) == EnumCrops.CROPS.COTTON.getBaseMeta()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        if (germling.isItemEqual(EnumSeeds.SEEDS.COTTON.getItemStack())){
            return true;
        }else{
            return false;
        }
    }
}
