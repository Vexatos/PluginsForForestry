package denoflionsx.plugins.FarmCraftory.Crops;

import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropHarvestTree implements ICropEntity {

    private World world;
    private int x;
    private int y;
    private int z;

    public cropHarvestTree(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList<ItemStack> i = new ArrayList();
        for (EnumCrops.TREE s : EnumCrops.TREE.values()) {
            int meta = world.getBlockMetadata(x, y, z);
            if (s.getTree().isGrown(meta)) {
                i.add(s.getTree().getFruitItem());
            }
        }
        world.setBlock(x, y, z, 0);
        return i;
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        int id = world.getBlockId(x, y, z);
        if (id != 0) {
            for (EnumCrops.TREE s : EnumCrops.TREE.values()) {
                if (s.getTree().isFruit(id)) {
                    if (s.getTree().isGrown(this.world.getBlockMetadata(x, y, z))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
