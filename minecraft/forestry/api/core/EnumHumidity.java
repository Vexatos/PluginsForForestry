package forestry.api.core;

import forestry.api.core.EnumHumidity$1;
import java.util.ArrayList;

public enum EnumHumidity
{
    ARID("Arid", 2),
    NORMAL("Normal", 1),
    DAMP("Damp", 4);
    public static ArrayList aridBiomeIds = new ArrayList();
    public static ArrayList dampBiomeIds = new ArrayList();
    public static ArrayList normalBiomeIds = new ArrayList();
    public final String name;
    public final int iconIndex;

    private EnumHumidity(String var3, int var4)
    {
        this.name = var3;
        this.iconIndex = var4;
    }

    public String getName()
    {
        return this.name;
    }

    public int getIconIndex()
    {
        return this.iconIndex;
    }

    public static ArrayList getBiomeIds(EnumHumidity var0)
    {
        switch (EnumHumidity$1.$SwitchMap$forestry$api$core$EnumHumidity[var0.ordinal()])
        {
            case 1:
                return aridBiomeIds;

            case 2:
                return dampBiomeIds;

            case 3:
            default:
                return normalBiomeIds;
        }
    }
}
