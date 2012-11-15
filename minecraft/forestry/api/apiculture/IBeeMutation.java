package forestry.api.apiculture;

import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;

public interface IBeeMutation extends IMutation
{
    int getChance(IBeeHousing var1, IAllele var2, IAllele var3, IGenome var4, IGenome var5);
}
