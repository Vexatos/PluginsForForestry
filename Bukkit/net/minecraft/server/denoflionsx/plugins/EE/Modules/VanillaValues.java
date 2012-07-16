package net.minecraft.server.denoflionsx.plugins.EE.Modules;

import net.minecraft.server.denoflionsx.plugins.pluginEE;
import java.util.HashMap;
import net.minecraft.server.Item;

public class VanillaValues
{
    public static HashMap values = new HashMap();

    public static void assignValues()
    {
        Item[] var0 = Item.byId;
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            Item var3 = var0[var2];

            if (var3 != null)
            {
                int var4 = var3.id;
                String var5 = var3.getName();
                int var6 = pluginEE.getEEValue(var4, 0);
                values.put(var5, Integer.valueOf(var6));
            }
        }
    }
}
