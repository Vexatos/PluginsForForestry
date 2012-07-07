package net.minecraft.server.denoflionsx.plugins.Redpower;

import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomSeed;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class cropFlax extends cropCustomSeed
{
    public cropFlax(World var1, int var2, int var3, int var4, Item var5, Item var6, int var7, int[] var8)
    {
        super(var1, var2, var3, var4, var5, var6, var7, var8);
    }

    public ArrayList doHarvest()
    {
        ArrayList var1 = new ArrayList();
        Random var2 = new Random();
        Random var3 = new Random();
        int var4 = var2.nextInt(this.plantamount) + 1;
        int var5 = var3.nextInt(this.seedamount) + 1;
        var1.add(new ItemStack(this.plantid, var4, 0));
        var1.add(new ItemStack(this.seedid, var5, 0));
        this.world.setTypeIdAndData(this.xCoord, this.yCoord + 1, this.zCoord, 0, 0);
        this.world.setTypeIdAndData(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }
}
