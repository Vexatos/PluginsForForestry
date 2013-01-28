package denoflionsx.PluginsforForestry.Integration.ForestryIntegration;

import denoflionsx.PluginsforForestry.Blocks.Plants.TileEntityOmniPlant;
import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OmniPlantCrop implements ICropEntity{

    private World world;
    private int x;
    private int y;
    private int z;

    public OmniPlantCrop(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList<ItemStack> a = new ArrayList();
        // Breaking the block drops the items.
        world.setBlockAndMetadataWithUpdate(x, y, z, 0, 0, true);
        return a;
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        TileEntityOmniPlant o = (TileEntityOmniPlant) world.getBlockTileEntity(x, y, z);
        if (o.getGrowthStage() == TileEntityOmniPlant.getFinalStage()){
            return true;
        }else{
            return false;
        }
    }
}
