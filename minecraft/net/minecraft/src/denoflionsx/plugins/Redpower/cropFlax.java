package net.minecraft.src.denoflionsx.plugins.Redpower;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.denoflionsx.plugins.BetterFarming.cropCustomSeed;

public class cropFlax extends cropCustomSeed {

    public cropFlax(World var1, int var2, int var3, int var4, Item plant, Item seed, int g, int[] z) {
        super(var1, var2, var3, var4, plant, seed, g, z);
    }

    @Override
    public ArrayList<ItemStack> doHarvest() {
        ArrayList var1 = new ArrayList();
        Random p = new Random();
        Random s = new Random();
        int p2 = p.nextInt(this.plantamount) + 1;
        int s2 = s.nextInt(this.seedamount) + 1;
        var1.add(new ItemStack(this.plantid, p2, 0));
        var1.add(new ItemStack(this.seedid, s2, 0));
        this.world.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord + 1, this.zCoord, 0, 0);
        this.world.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 0);
        return var1;
    }
}
