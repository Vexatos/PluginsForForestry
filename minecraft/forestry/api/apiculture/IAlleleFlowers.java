package forestry.api.apiculture;

import forestry.api.genetics.IAllele;

public interface IAlleleFlowers extends IAllele
{
    IFlowerProvider getProvider();
}
