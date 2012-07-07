package net.minecraft.server.denoflionsx.plugins.Buildcraft.BC3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.server.ItemStack;

public class addLiquidBC3
{
    public static void add(int var0, ItemStack var1)
    {
        try
        {
            Class var2 = Class.forName("buildcraft.api.BuildCraftAPI");
            Field var3 = var2.getField("liquids");
            Class var4 = Class.forName("buildcraft.api.LiquidData");
            Constructor var6 = var4.getDeclaredConstructor(new Class[] {Integer.TYPE, Integer.TYPE, ItemStack.class});
            Object var5 = var6.newInstance(new Object[] {Integer.valueOf(var0), Integer.valueOf(var0), var1});
            LinkedList var7 = (LinkedList)var3.get((Object)null);
            var7.add(var4.cast(var5));
            var2.getField("liquids").set((Object)null, var7);
        }
        catch (Exception var8)
        {
            ;
        }
    }

    public static void add(int var0, ItemStack var1, ItemStack var2)
    {
        try
        {
            Class var3 = Class.forName("buildcraft.api.BuildCraftAPI");
            Field var4 = var3.getField("liquids");
            Class var5 = Class.forName("buildcraft.api.LiquidData");
            Constructor var7 = var5.getDeclaredConstructor(new Class[] {Integer.TYPE, Integer.TYPE, ItemStack.class, ItemStack.class});
            Object var6 = var7.newInstance(new Object[] {Integer.valueOf(var0), Integer.valueOf(var0), var1, var2});
            LinkedList var8 = (LinkedList)var4.get((Object)null);
            var8.add(var5.cast(var6));
            var3.getField("liquids").set((Object)null, var8);
        }
        catch (Exception var9)
        {
            ;
        }
    }
}
