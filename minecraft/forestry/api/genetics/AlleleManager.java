package forestry.api.genetics;

import cpw.mods.fml.common.FMLLog;

public class AlleleManager
{
    public static IAlleleRegistry alleleRegistry;

    public static IAllele getAllele(String var0)
    {
        IAllele var1 = null;

        try
        {
            String var2 = "forestry.core.genetics.Allele";
            Object var3 = Class.forName(var2).getField(var0).get((Object)null);

            if (var3 instanceof IAllele)
            {
                var1 = (IAllele)var3;
            }
        }
        catch (Exception var4)
        {
            FMLLog.warning("Could not retrieve bee allele identified by: " + var0, new Object[0]);
        }

        return var1;
    }
}
