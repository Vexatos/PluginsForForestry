package denoflionsx.plugins.Redpower;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.World;
import denoflionsx.plugins.BetterFarming.cropCustomSeedProvider;
import forestry.api.cultivation.ICropEntity;

public class cropFlaxProvider extends cropCustomSeedProvider{
    
    protected int plantamount;
    protected int seedamount;

    public cropFlaxProvider(Item p, Item s, Block pb, int g, int[]z) {
        super(p, s, pb, g);
        this.plantamount = z[0];
        this.seedamount = z[1];
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropFlax(world, x, y, z, this.plant, this.seed, this.grownmeta, new int[]{this.plantamount,this.seedamount});
    }
}
