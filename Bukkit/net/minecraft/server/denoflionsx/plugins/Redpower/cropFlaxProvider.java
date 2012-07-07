package net.minecraft.server.denoflionsx.plugins.Redpower;

import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomSeedProvider;
import forestry.api.cultivation.ICropEntity;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.World;

public class cropFlaxProvider extends cropCustomSeedProvider
{
    protected int plantamount;
    protected int seedamount;

    public cropFlaxProvider(Item var1, Item var2, Block var3, int var4, int[] var5)
    {
        super(var1, var2, var3, var4);
        this.plantamount = var5[0];
        this.seedamount = var5[1];
    }

    public ICropEntity getCrop(World var1, int var2, int var3, int var4)
    {
        return new cropFlax(var1, var2, var3, var4, this.plant, this.seed, this.grownmeta, new int[] {this.plantamount, this.seedamount});
    }
}
