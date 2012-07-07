package net.minecraft.server.denoflionsx.denLib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class denLib
{
    public static boolean detect(String var0)
    {
        return ModLoader.isModLoaded(var0);
    }

    public static Item getItem(String var0, String var1)
    {
        boolean var2 = false;
        Item var3 = null;

        try
        {
            var3 = (Item)Class.forName(var0).getField(var1).get((Object)null);
            var2 = true;
        }
        catch (Exception var8)
        {
            print("" + var8);
            var2 = false;
        }
        finally
        {
            if (var2)
            {
                ;
            }
        }

        return var3;
    }

    public static ItemStack getItemStack(String var0, String var1)
    {
        boolean var2 = false;
        ItemStack var3 = null;

        try
        {
            var3 = (ItemStack)Class.forName(var0).getField(var1).get((Object)null);
            var2 = true;
        }
        catch (Exception var8)
        {
            print("" + var8);
            var2 = false;
        }
        finally
        {
            if (var2)
            {
                ;
            }

            return var3;
        }
    }

    public static Block getBlock(String var0, String var1)
    {
        boolean var2 = false;
        Block var3 = null;

        try
        {
            var3 = (Block)Class.forName(var0).getField(var1).get((Object)null);
            var2 = true;
        }
        catch (Exception var8)
        {
            print("" + var8);
            var2 = false;
        }
        finally
        {
            if (var2)
            {
                ;
            }
        }

        return var3;
    }

    public static void print(String var0)
    {
        System.out.println(var0);
    }

    public static boolean convertToBoolean(String var0)
    {
        var0 = var0.toLowerCase();
        return var0.equals("true");
    }

    public static void classSnoop(String var0)
    {
        try
        {
            Class var1 = Class.forName(var0);
            print("--------------------");
            print("Class Snoop Engaged");
            print("Class: " + var0);
            print("--------------------");
            Method[] var2 = var1.getDeclaredMethods();
            Field[] var3 = var1.getDeclaredFields();
            print("Defined Methods");
            print("--------------------");
            int var4;

            for (var4 = 0; var4 < var2.length; ++var4)
            {
                print(var2[var4].getName());
            }

            print("--------------------");
            print("Defined Fields");
            print("--------------------");

            for (var4 = 0; var4 < var3.length; ++var4)
            {
                print(var3[var4].getName());
            }

            print("--------------------");
            print("End of Snoop");
            print("--------------------");
        }
        catch (Exception var5)
        {
            ;
        }
    }
}
