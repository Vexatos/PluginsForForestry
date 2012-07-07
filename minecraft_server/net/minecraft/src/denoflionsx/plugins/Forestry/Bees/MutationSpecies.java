package net.minecraft.src.denoflionsx.plugins.Forestry.Bees;

import net.minecraft.src.World;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;
/**
 *
 * @author denoflions
 */
public class MutationSpecies implements IMutation {

    private IAllele[] template = new IAllele[EnumBeeChromosome.values().length];
    private IAllele[] Allele0and1 = new IAllele[2];
    private int[] chances = new int[2];
    private boolean isSecret;

    public MutationSpecies(IAllele[] par1, IAllele[] par2, int[] par3, boolean par4) {

        this.template = par1;
        this.Allele0and1 = par2;
        this.chances = par3;
        this.isSecret = par4;

    }

    @Override
    public IAllele getAllele0() {
        return this.Allele0and1[0];
    }

    @Override
    public IAllele getAllele1() {
        return this.Allele0and1[1];
    }

    @Override
    public int getBaseChance() {
        return this.chances[0];
    }

    @Override
    public IAllele getPartner(IAllele allele) {
        if (allele.getId() == this.Allele0and1[0].getId()) {
            return this.getAllele1();
        } else {
            return this.getAllele0();
        }
    }

    @Override
    public IAllele[] getTemplate() {
        return this.template;
    }

    @Override
    public boolean isPartner(IAllele allele) {
        return allele.getId() == this.Allele0and1[1].getId() || allele.getId() == this.Allele0and1[0].getId();
    }

    @Override
    public boolean isSecret() {
        return this.isSecret;
    }

    @Override
    public int getChance(World world, int biomeid, int x, int y, int z, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
