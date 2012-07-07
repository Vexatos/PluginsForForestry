package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.items.Tools;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.TankManager;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.goldGear;
import net.minecraft.server.denoflionsx.plugins.Forestry.pipette;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginCore
{
    public static Tools metaItem;
    public static ItemStack[] containers = new ItemStack[4];
    public static ItemStack[] filled = new ItemStack[4];
    public static final boolean isRelease = false;
    public static final boolean debugmode = false;
    public static final int bottle = (int)Math.floor(250.0D);
    public static HashMap plugins = new HashMap();

    public static void registerLatePlugins()
    {
        try
        {
            plugins.put("RedPower", new pluginRedpower());
            plugins.put("RedPowerWorld", new pluginRedPowerWorld());
            plugins.put("EE", new pluginEE());
        }
        catch (Exception var1)
        {
            var1.printStackTrace();
        }
    }

    public static void registerEarlyPlugins()
    {
        containers[0] = new ItemStack(Item.BUCKET, 1, 0);
        containers[1] = ItemInterface.getItem("waxCapsule");
        containers[2] = ItemInterface.getItem("canEmpty");
        containers[3] = ItemInterface.getItem("refractoryEmpty");
        filled[0] = new ItemStack(Item.MILK_BUCKET, 1, 0);
        metaItem = new Tools(Integer.valueOf(core.config.getOption("liquidVacuum_ID")).intValue(), "multiItem");
        metaItem.metaMap.put("Milker", Integer.valueOf(0));
        metaItem.metaMap.put("Milk Capsule", Integer.valueOf(1));
        metaItem.metaMap.put("Milk Can", Integer.valueOf(2));
        metaItem.metaMap.put("Milk Capsule_Red", Integer.valueOf(3));
        metaItem.metaMap.put("Heavy Water Capsule", Integer.valueOf(4));
        metaItem.metaMap.put("Heavy Water Can", Integer.valueOf(5));
        metaItem.metaMap.put("Heavy Water Capsule_Red", Integer.valueOf(6));
        metaItem.metaMap.put("Heavy Water Bottle", Integer.valueOf(7));
        metaItem.metaMap.put("Heavy Water Cell", Integer.valueOf(8));
        metaItem.metaMap.put("Milk Bottle", Integer.valueOf(9));
        metaItem.metaMap.put("Milk Cell", Integer.valueOf(10));
        metaItem.metaMap.put("Wooden Bucket", Integer.valueOf(11));
        metaItem.metaMap.put("Filled Wooden Bucket", Integer.valueOf(12));
        metaItem.metaMap.put("Debug Tool", Integer.valueOf(13));
        metaItem.metaMap.put("Milk Bag", Integer.valueOf(14));
        metaItem.add("liquidvacuum", ((Integer)metaItem.metaMap.get("Milker")).intValue(), 2, "Liquid Vacuum", 1);
        metaItem.add("woodenbucket", ((Integer)metaItem.metaMap.get("Wooden Bucket")).intValue(), 46, "Wooden Bucket", 1);
        metaItem.add("filledwoodenbucket", ((Integer)metaItem.metaMap.get("Filled Wooden Bucket")).intValue(), 45, "Filled Wooden Bucket", 1);
        metaItem.add("milkbag", ((Integer)metaItem.metaMap.get("Milk Bag")).intValue(), 7, "Milk Bag");
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(Block.STATIONARY_WATER, 1000), new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Wooden Bucket")).intValue()), new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Filled Wooden Bucket")).intValue()));

        try
        {
            if (denLib.convertToBoolean(core.config.getOption("pluginBetterFarming_Enabled")))
            {
                plugins.put("BetterFarming", new pluginBetterFarming());
            }

            if (denLib.convertToBoolean(core.config.getOption("pluginMineFactoryReloaded_Enabled")))
            {
                plugins.put("MFR", new pluginMineFactoryReloaded());
            }

            if (denLib.convertToBoolean(core.config.getOption("pluginForestry_Enabled")))
            {
                plugins.put("Forestry", new pluginForestry());
                plugins.put("IC2", new pluginIC2());
            }

            if (denLib.convertToBoolean(core.config.getOption("pluginBuildcraft_Enabled")))
            {
                plugins.put("Buildcraft", new pluginBuildcraft());
            }

            if (denLib.convertToBoolean(core.config.getOption("pluginAdvancedPowerSystems_Enabled")))
            {
                plugins.put("APS", new pluginAdvancedPowerSystems());
            }

            ItemStack var0 = new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Milker")).intValue());
            Object[] var1;

            if (((pluginBase)plugins.get("Forestry")).loaded)
            {
                var1 = new Object[] {"PpP", "SgS", "GBG", 'P', ItemInterface.getItem("propolis"), 'p', new ItemStack(pipette.pipette), 'S', ItemInterface.getItem("sturdyMachine"), 'g', new ItemStack(Item.GLASS_BOTTLE), 'G', ItemInterface.getItem("gearBronze"), 'B', new ItemStack(Item.BUCKET)};
                RecipeManagers.carpenterManager.addRecipe(100, new LiquidStack(Block.STATIONARY_WATER.id, 1000), (ItemStack)null, var0, var1);
            }
            else if (((pluginBase)plugins.get("Buildcraft")).loaded && !((pluginBase)plugins.get("Forestry")).loaded)
            {
                var1 = new Object[] {"LgL", "ITI", "GBG", 'L', Item.LEATHER, 'g', Item.GLASS_BOTTLE, 'I', Block.IRON_BLOCK, 'T', TankManager.TankBlock, 'G', goldGear.goldGear, 'B', Item.BUCKET};
                ModLoader.addRecipe(var0, var1);
            }

            ModLoader.addRecipe(new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Wooden Bucket")).intValue()), new Object[] {"XXX", "PXP", "XPX", 'P', Block.LOG});
            ModLoader.addRecipe(new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Wooden Bucket")).intValue()), new Object[] {"PXP", "XPX", "XXX", 'P', Block.LOG});

            for (int var3 = 0; var3 < containers.length; ++var3)
            {
                ModLoader.addShapelessRecipe(filled[var3], new Object[] {new ItemStack(metaItem, 1, ((Integer)metaItem.metaMap.get("Milk Bag")).intValue()), containers[var3]});
            }
        }
        catch (Exception var2)
        {
            var2.printStackTrace();
        }
    }
}
