package forestry.api.genetics;

import java.util.LinkedHashMap;
import net.minecraft.src.World;

public interface IAlleleRegistry
{
    LinkedHashMap getRegisteredAlleles();

    LinkedHashMap getRegisteredBranches();

    void registerAllele(IAllele var1);

    IAllele getAllele(String var1);

    void reloadMetaMap(World var1);

    IAllele getFromMetaMap(int var1);

    int getFromUIDMap(String var1);

    void registerBranch(IBranch var1);

    IBranch getBranch(String var1);

    void registerAlleleHandler(IAlleleHandler var1);
}
