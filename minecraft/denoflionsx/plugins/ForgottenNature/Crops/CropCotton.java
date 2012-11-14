package denoflionsx.plugins.ForgottenNature.Crops;

import denoflionsx.plugins.ForgottenNature.Goods.EnumGoods;
import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class CropCotton implements ICropEntity{

    private World world;
    private int x;
    private int y;
    private int z;
    private int id;
    private int meta;

    public CropCotton(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world.getBlockId(x, y, z);
        this.world.getBlockMetadata(x, y, z);
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList<ItemStack> drops = new ArrayList();
        this.world.setBlock(x, y, z, 0);
        this.world.setBlockMetadataWithNotify(x, y, z, 0);
        drops.add(EnumGoods.GOOD.COTTON.getItemStack());
        return drops;
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        if (this.id == EnumCrops.hempCottonBambooCornID && this.meta == EnumCrops.CROPS.COTTON.getGrownMeta()){
            return true;
        }else{
            return false;
        }
    }
 
}
