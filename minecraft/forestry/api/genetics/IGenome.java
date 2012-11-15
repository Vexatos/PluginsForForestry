package forestry.api.genetics;

import forestry.api.core.INBTTagable;

public interface IGenome extends INBTTagable
{
    IAlleleSpecies getPrimary();

    IAlleleSpecies getSecondary();

    IChromosome[] getChromosomes();

    IAllele getActiveAllele(int var1);

    IAllele getInactiveAllele(int var1);
}
