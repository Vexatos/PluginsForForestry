package net.minecraft.server.denoflionsx.plugins.Buildcraft.BC2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.server.Item;

public class addLiquidBC2
{
    public static void add(int var0, Item var1)
    {
        try
        {
            Class var2 = Class.forName("buildcraft.api.API");
            Field var3 = var2.getField("liquids");
            Class var4 = Class.forName("buildcraft.api.LiquidData");
            Constructor var6 = var4.getDeclaredConstructor(new Class[] {Integer.TYPE, Integer.TYPE});
            Object var5 = var6.newInstance(new Object[] {Integer.valueOf(var0), Integer.valueOf(var1.id)});
            LinkedList var7 = (LinkedList)var3.get((Object)null);
            var7.add(var4.cast(var5));
            var2.getField("liquids").set(var7, var7);
        }
        catch (Exception var9)
        {
            var9.printStackTrace();
        }
    }
}
