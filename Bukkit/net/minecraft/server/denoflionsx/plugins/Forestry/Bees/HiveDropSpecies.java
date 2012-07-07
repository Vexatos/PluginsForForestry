package net.minecraft.server.denoflionsx.plugins.Forestry.Bees;

import forestry.api.apiculture.IHiveDrop;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IMutation;
import net.minecraft.server.World;

public class HiveDropSpecies implements IHiveDrop
{
    private IMutation bee;
    private int chance;

    public HiveDropSpecies(IMutation var1, int var2)
    {
        this.bee = var1;
        this.chance = var2;
    }

    public HiveDropSpecies(IMutation var1)
    {
        this.bee = var1;
        this.chance = 100;
    }

    public int getChance(World var1, int var2, int var3, int var4)
    {
        return this.chance;
    }

    public IAllele[] getTemplate()
    {
        return this.bee.getTemplate();
    }
}
