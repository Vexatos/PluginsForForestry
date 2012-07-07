package net.minecraft.server.denoflionsx.plugins.BetterFarming;

import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class cropCustomSeedProvider implements ICropProvider
{
    protected Item plant;
    protected Item seed;
    protected Block plantBlock;
    protected int grownmeta;

    public cropCustomSeedProvider(Item var1, Item var2, Block var3, int var4)
    {
        this.plant = var1;
        this.seed = var2;
        this.plantBlock = var3;
        this.grownmeta = var4;
    }

    public boolean doPlant(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        int var6 = var2.getTypeId(var3, var4, var5);

        if (var6 != 0)
        {
            return false;
        }
        else
        {
            int var7 = var2.getTypeId(var3, var4 - 1, var5);

            if (var7 != Block.SOIL.id)
            {
                return false;
            }
            else
            {
                var2.setTypeIdAndData(var3, var4, var5, this.plantBlock.id, 0);
                return true;
            }
        }
    }

    public ICropEntity getCrop(World var1, int var2, int var3, int var4)
    {
        return new cropCustomSeed(var1, var2, var3, var4, this.plant, this.seed, this.grownmeta);
    }

    public ItemStack[] getWindfall()
    {
        return null;
    }

    public boolean isCrop(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getTypeId(var2, var3, var4);
        return var5 == this.plantBlock.id;
    }

    public boolean isGermling(ItemStack var1)
    {
        return var1.id == this.seed.id;
    }
}
