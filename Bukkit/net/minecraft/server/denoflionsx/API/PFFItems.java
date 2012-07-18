package net.minecraft.server.denoflionsx.API;

import java.util.HashMap;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class PFFItems
{
    public static HashMap registeredItems = new HashMap();

    public static void registerItem(String var0, Item var1, int var2)
    {
        ItemStack var3 = new ItemStack(var1, 1, var2);
        registeredItems.put(var0, var3);
    }
}
