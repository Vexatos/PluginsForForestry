package net.minecraft.server.denoflionsx.plugins.Forestry;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.pluginCore;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.BC2.addLiquidBC2;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.BC3.addLiquidBC3;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.Iterator;
import java.util.Map.Entry;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class LiquidContainerSystem
{
    private static int bucket = 1000;

    public static void create(multiItem var0)
    {
        Iterator var1 = var0.metaMap.entrySet().iterator();

        while (var1.hasNext())
        {
            Entry var2 = (Entry)var1.next();
            String var3 = var2.getKey().toString();
            Integer var4 = Integer.valueOf(var2.getValue().toString());
            ItemStack var5 = new ItemStack(var0, 1, var4.intValue());
            ItemStack var6;

            if (var3.contains("Capsule") && !var3.contains("_Red"))
            {
                var6 = ItemInterface.getItem("waxCapsule");
                genericCap(var0, var5, var6);
            }
            else if (var3.contains("Capsule_Red"))
            {
                var6 = ItemInterface.getItem("refractoryEmpty");
                genericCap(var0, var5, var6);
            }
            else if (var3.contains("Can"))
            {
                var6 = ItemInterface.getItem("canEmpty");
                genericCap(var0, var5, var6);
            }
            else if (var3.contains("Bucket"))
            {
                bucket(var0, var5);
            }
            else if (var3.contains("Bottle"))
            {
                bottle(var0, var5);
            }
        }
    }

    public static void createWithOverride(multiItem var0, int var1, ItemStack var2, boolean var3)
    {
        Iterator var4 = var0.metaMap.entrySet().iterator();

        while (var4.hasNext())
        {
            Entry var5 = (Entry)var4.next();
            String var6 = var5.getKey().toString();
            Integer var7 = Integer.valueOf(var5.getValue().toString());
            ItemStack var8 = new ItemStack(var0, 1, var7.intValue());
            ItemStack var9;

            if (var6.contains("Capsule") && !var6.contains("_Red"))
            {
                var9 = ItemInterface.getItem("waxCapsule");
                genericCapWithOverride(var0, var8, var9, var1);
            }
            else if (var6.contains("Capsule_Red"))
            {
                var9 = ItemInterface.getItem("refractoryEmpty");
                genericCapWithOverride(var0, var8, var9, var1);
            }
            else if (var6.contains("Can"))
            {
                var9 = ItemInterface.getItem("canEmpty");
                genericCapWithOverride(var0, var8, var9, var1);
            }
            else if (var6.contains("Bucket"))
            {
                if (!var3)
                {
                    bucket(var0, var8);
                }
            }
            else if (var6.contains("Bottle"))
            {
                bottleOverride(var2, var1);
            }
        }
    }

    public static void genericCap(multiItem var0, ItemStack var1, ItemStack var2)
    {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var0.id, bucket), var1, var2, false));
        unpack(var1, var0.id, bucket);
        pack(var2, var1, var0.id, bucket);
    }

    public static void genericCapWithOverride(multiItem var0, ItemStack var1, ItemStack var2, int var3)
    {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var3, bucket), var1, var2, false));
        unpack(var1, var3, bucket);
        pack(var2, var1, var3, bucket);
    }

    public static void bucket(multiItem var0, ItemStack var1)
    {
        ItemStack var2 = new ItemStack(Item.BUCKET);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var0.id, bucket), var1, var2, true));
        unpack(var1, var0.id, bucket);
        pack(var2, var1, var0.id, bucket);

        if (denLib.detect("mod_BuildCraftSilicon"))
        {
            addLiquidBC3.add(var0.id, var1);
        }
        else if (denLib.detect("mod_BuildCraftCore") && denLib.convertToBoolean(core.config.getOption("EnableBC2TankIntegration")))
        {
            addLiquidBC2.add(var0.id, var1.getItem());
        }
    }

    public static void bottle(multiItem var0, ItemStack var1)
    {
        ItemStack var2 = new ItemStack(Item.GLASS_BOTTLE);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var0.id, pluginCore.bottle), var1, var2, false));
        unpack(var1, var0.id, pluginCore.bottle);
        pack(var2, var1, var0.id, pluginCore.bottle);
    }

    public static void bottleOverride(ItemStack var0, int var1)
    {
        ItemStack var2 = new ItemStack(Item.GLASS_BOTTLE);
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var1, pluginCore.bottle), var0, var2, false));
        unpack(var0, var1, pluginCore.bottle);
        pack(var2, var0, var1, pluginCore.bottle);
    }

    public static void unpack(ItemStack var0, int var1, int var2)
    {
        RecipeManagers.squeezerManager.addRecipe(5, new ItemStack[] {var0}, new LiquidStack(var1, var2));
    }

    public static void pack(ItemStack var0, ItemStack var1, int var2, int var3)
    {
        RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(var2, var3), var0, var1);
    }

    public static void registerMilkBucket(int var0)
    {
        LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(var0, bucket), new ItemStack(Item.MILK_BUCKET), new ItemStack(Item.MILK_BUCKET), true));
        pack(new ItemStack(Item.BUCKET), new ItemStack(Item.MILK_BUCKET), var0, bucket);
        unpack(new ItemStack(Item.MILK_BUCKET), var0, bucket);
    }
}
