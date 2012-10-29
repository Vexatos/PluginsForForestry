package denoflionsx.plugins.Millenaire.Crops;

import forestry.api.cultivation.ICropEntity;
import java.util.ArrayList;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropMudBrick implements ICropEntity{
    
    public World world;
    public int x;
    public int y;
    public int z;

    public cropMudBrick(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ItemStack block = new ItemStack(this.world.getBlockId(x, y, z),1,this.world.getBlockMetadata(x, y, z));
        ArrayList<ItemStack> a = new ArrayList();
        a.add(block);
        this.world.setBlockAndMetadataWithUpdate(x, y, z, 0, 0, true);
        this.world.setBlockAndMetadataWithUpdate(x, y - 1, z, Block.tilledField.blockID, 0, true);
        return a;
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        if (this.world.getBlockId(x,y,z) == 0){
            return false;
        }
        return Block.blocksList[this.world.getBlockId(x,y,z)].getBlockName().equals("tile.ml_stone_deco");
    }
}
