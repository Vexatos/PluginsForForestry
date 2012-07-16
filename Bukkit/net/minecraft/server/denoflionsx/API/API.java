package net.minecraft.server.denoflionsx.API;

import net.minecraft.server.ItemStack;

public class API
{
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
}
