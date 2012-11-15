package forestry.api.arboriculture;

import forestry.api.genetics.IAllele;

public interface IAlleleFruit extends IAllele
{
    IFruitProvider getProvider();
}
