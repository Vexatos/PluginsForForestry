package denoflionsx.plugins.BetterFarming;

import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import forestry.api.cultivation.ICropEntity;

public class cropCustomSeed implements ICropEntity {

    protected World world;
    protected int xCoord;
    protected int yCoord;
    protected int zCoord;
    protected int meta;
    protected int plantid;
    protected int seedid;
    protected int grownmeta;
    protected int plantamount;
    protected int seedamount;

    public cropCustomSeed(World var1, int var2, int var3, int var4, Item plant, Item seed, int g) {

        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.meta = var1.getBlockMetadata(var2, var3, var4);
        this.plantid = plant.shiftedIndex;
        this.seedid = seed.shiftedIndex;
        this.grownmeta = g;
        this.plantamount = 1;
        this.seedamount = 2;

    }
    
        public cropCustomSeed(World var1, int var2, int var3, int var4, Item plant, Item seed, int g, int[] z) {

        this.world = var1;
        this.xCoord = var2;
        this.yCoord = var3;
        this.zCoord = var4;
        this.meta = var1.getBlockMetadata(var2, var3, var4);
        this.plantid = plant.shiftedIndex;
        this.seedid = seed.shiftedIndex;
        this.grownmeta = g;
        this.plantamount = z[0];
        this.seedamount = z[1];

    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList var1 = new ArrayList();
        var1.add(new ItemStack(this.plantid,this.plantamount,0));
        var1.add(new ItemStack(this.seedid,this.seedamount,0));
        this.world.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }

    @Override
    public int[] getNextPosition() {
        return null;
    }

    @Override
    public boolean isHarvestable() {
        return this.meta >= this.grownmeta;
    }
}
