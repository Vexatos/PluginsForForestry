package net.minecraft.server.denoflionsx.plugins.EE;

import java.util.HashMap;

public class customEMCParser
{
    public static HashMap Values = new HashMap();

    public static void parse(String var0)
    {
        String[] var1 = var0.split(",");
        int var2 = Integer.valueOf(var1[0]).intValue();
        int var3 = Integer.valueOf(var1[1]).intValue();
        int var4 = Integer.valueOf(var1[2]).intValue();
        HashMap var5 = new HashMap();

        if (Values.get(Integer.valueOf(var2)) != null)
        {
            var5 = (HashMap)Values.get(Integer.valueOf(var2));
        }

        var5.put(Integer.valueOf(var3), Integer.valueOf(var4));
        Values.put(Integer.valueOf(var2), var5);
    }
}
