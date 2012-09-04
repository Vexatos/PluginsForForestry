package denoflionsx.plugins.BetterFarming;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;

public class cropCustomSeedProvider implements ICropProvider {
    
    protected Item plant;
    protected Item seed;
    protected Block plantBlock;
    protected int grownmeta;

    public cropCustomSeedProvider(Item p, Item s, Block pb, int g) {
        this.plant = p;
        this.seed = s;
        this.plantBlock = pb;
        this.grownmeta = g;
    }

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int var6 = world.getBlockId(x, y, z);

        if (var6 != 0) {
            return false;
        } else {
            int var7 = world.getBlockId(x, y - 1, z);

            if (var7 != Block.tilledField.blockID) {
                return false;
            } else {
                world.setBlockAndMetadataWithNotify(x, y, z, this.plantBlock.blockID, 0);
                return true;
            }
        }
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropCustomSeed(world, x, y, z,this.plant,this.seed,this.grownmeta);
    }

    @Override
    public ItemStack[] getWindfall() {
        return null;
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        int var5 = world.getBlockId(x, y, z);
        return var5 == this.plantBlock.blockID;
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        return germling.itemID == this.seed.shiftedIndex;
    }
}
