package forestry.api.arboriculture;

import forestry.api.genetics.IAllele;

public interface IAlleleGrowth extends IAllele
{
    IGrowthProvider getProvider();
}
