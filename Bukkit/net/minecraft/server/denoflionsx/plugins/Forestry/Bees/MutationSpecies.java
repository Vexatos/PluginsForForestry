package net.minecraft.server.denoflionsx.plugins.Forestry.Bees;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;
import net.minecraft.server.World;

public class MutationSpecies implements IMutation
{
    private IAllele[] template = new IAllele[EnumBeeChromosome.values().length];
    private IAllele[] Allele0and1 = new IAllele[2];
    private int[] chances = new int[2];
    private boolean isSecret;

    public MutationSpecies(IAllele[] var1, IAllele[] var2, int[] var3, boolean var4)
    {
        this.template = var1;
        this.Allele0and1 = var2;
        this.chances = var3;
        this.isSecret = var4;
    }

    public IAllele getAllele0()
    {
        return this.Allele0and1[0];
    }

    public IAllele getAllele1()
    {
        return this.Allele0and1[1];
    }

    public int getBaseChance()
    {
        return this.chances[0];
    }

    public IAllele getPartner(IAllele var1)
    {
        return var1.getId() == this.Allele0and1[0].getId() ? this.getAllele1() : this.getAllele0();
    }

    public IAllele[] getTemplate()
    {
        return this.template;
    }

    public boolean isPartner(IAllele var1)
    {
        return var1.getId() == this.Allele0and1[1].getId() || var1.getId() == this.Allele0and1[0].getId();
    }

    public boolean isSecret()
    {
        return this.isSecret;
    }

    public int getChance(World var1, int var2, int var3, int var4, int var5, IAllele var6, IAllele var7, IGenome var8, IGenome var9)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
