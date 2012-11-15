package forestry.api.arboriculture;

import forestry.api.genetics.IAllele;

public interface ITreeBreedingManager
{
    void registerTreeTemplate(IAllele[] var1);

    void registerTreeTemplate(String var1, IAllele[] var2);

    IAllele[] getTreeTemplate(String var1);

    IAllele[] getDefaultTreeTemplate();
}
