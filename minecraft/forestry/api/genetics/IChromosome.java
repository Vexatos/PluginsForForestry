package forestry.api.genetics;

import forestry.api.core.INBTTagable;

public interface IChromosome extends INBTTagable
{
    IAllele getPrimaryAllele();

    IAllele getSecondaryAllele();

    IAllele getInactiveAllele();

    IAllele getActiveAllele();
}
