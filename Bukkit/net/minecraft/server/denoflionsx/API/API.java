package net.minecraft.server.denoflionsx.API;

import java.lang.reflect.Method;
import net.minecraft.server.ItemStack;

public class API
{
    public static final boolean verbose = false;

    public static ItemStack getItem(String var0)
    {
        ItemStack var1 = (ItemStack)PFFItems.registeredItems.get(var0);

        if (var1 != null)
        {
            return var1;
        }
        else
        {
            System.out.println("Unable to retrieve item: " + var0 + " from Plugins for Forestry!");
            return null;
        }
    }

    public static boolean isPluginLoaded(String var0)
    {
        String var2 = API.class.getPackage().toString();
        String var3 = "package denoflionsx.API";
        String var4 = "denoflionsx.plugins.pluginCore";

        if (!var2.equals(var3))
        {
            var4 = "net.minecraft.server." + var4;
        }

        try
        {
            Class var5 = Class.forName(var4);
            Method var6 = var5.getMethod("isPluginAlive", new Class[] {String.class});
            String var7 = var6.invoke((Object)null, new Object[] {var0}).toString();
            boolean var1;

            if (var7.toLowerCase().equals("true"))
            {
                var1 = true;
            }
            else
            {
                var1 = false;
            }

            return var1;
        }
        catch (Exception var8)
        {
            var8.printStackTrace();
            return false;
        }
    }
}
