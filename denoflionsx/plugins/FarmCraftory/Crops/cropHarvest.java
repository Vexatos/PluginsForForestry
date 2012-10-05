package denoflionsx.plugins.FarmCraftory.Crops;

import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropHarvest implements ICropEntity {
    
    public static boolean cheatMode = false;

    public static final int fullyGrown = 2;
    public World world;
    public int x;
    public int y;
    public int z;
    public Block tile;

    public cropHarvest(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        world.setBlock(x, y, z, 0);
        return new ArrayList();
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        if (CropTileEntityHelper.getHelperType(world.getBlockTileEntity(x, y, z)).getGrowthStage(world, x, y, z) == fullyGrown) {
            return true;
        } else {
            if (cheatMode){
              CropTileEntityHelper.getHelperType(world.getBlockTileEntity(x,y,z)).setGrowthStage(world, x, y, z, CropTileEntityHelper.getHelperType(world.getBlockTileEntity(x,y,z)).getGrowthStage(world, x, y, z) + 1);  
            }   
            return false;
        }
    }
}
