package net.minecraft.server.denoflionsx.plugins.EE;

import java.util.HashMap;

public class customEMCParser
{
    public HashMap Values = new HashMap();

    public void parse(String var1)
    {
        String[] var2 = var1.split(",");
        int var3 = Integer.valueOf(var2[0]).intValue();
        int var4 = Integer.valueOf(var2[1]).intValue();
        int var5 = Integer.valueOf(var2[2]).intValue();
        HashMap var6 = new HashMap();

        if (this.Values.get(Integer.valueOf(var3)) != null)
        {
            var6 = (HashMap)this.Values.get(Integer.valueOf(var3));
        }

        var6.put(Integer.valueOf(var4), Integer.valueOf(var5));
        this.Values.put(Integer.valueOf(var3), var6);
    }
}
