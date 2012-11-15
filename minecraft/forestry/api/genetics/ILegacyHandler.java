package forestry.api.genetics;

public interface ILegacyHandler
{
    void registerLegacyMapping(int var1, String var2);

    IAllele getFromLegacyMap(int var1);
}
