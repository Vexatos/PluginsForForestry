package net.minecraft.server.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.items.waxBlock;
import net.minecraft.server.denoflionsx.items.waxBlockItem;
import net.minecraft.server.denoflionsx.items.waxCast;
import net.minecraft.server.denoflionsx.items.waxSlab;
import net.minecraft.server.denoflionsx.items.waxSlabItem;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.ModLoader;

public class blueswaxModule extends baseModule
{
    public static waxCast extrawax;
    public static waxBlock thatch;
    public static waxSlab thatchslab;

    public blueswaxModule(pluginBase var1)
    {
        super(var1);
    }

    public static void load(pluginBase var0)
    {
        blueswaxModule var1 = new blueswaxModule(var0);
        var1.register();
    }

    protected void defaults()
    {
        this.parent.config.addDefault("# These options are for Blue\'s Wax Stuff");
        this.parent.config.addDefault("# Includes Wax Casts, wand of freezing, wax tablets");
        this.parent.config.addDefault("BluesWaxStuff_Enabled=true");
        this.parent.config.addDefault("BluesWaxStuff_ItemID=5317");
    }

    protected void init()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("BluesWaxStuff_Enabled")))
        {
            extrawax = new waxCast(Integer.valueOf(this.parent.config.getOption("BluesWaxStuff_ItemID")).intValue(), "bluesextrawaxitems");
            extrawax.metaMap.put("BLANK SPACE DO NOT USE", Integer.valueOf(0));
            extrawax.metaMap.put("Wax Tablet", Integer.valueOf(1));
            extrawax.metaMap.put("Wax Cast", Integer.valueOf(2));
            extrawax.metaMap.put("Filled Wax Cast", Integer.valueOf(3));
            extrawax.metaMap.put("Refractory Cast", Integer.valueOf(4));
            extrawax.metaMap.put("Filled Wax Cast_Red", Integer.valueOf(5));
            extrawax.metaMap.put("Lava Cast", Integer.valueOf(6));
            extrawax.metaMap.put("Rod of Freezing", Integer.valueOf(7));
            extrawax.metaMap.put("Test", Integer.valueOf(8));
            extrawax.add("waxtablet", ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue(), 15, "Wax Tablet");
            extrawax.add("waxcast", ((Integer)extrawax.metaMap.get("Wax Cast")).intValue(), 14, "Wax Cast");
            extrawax.add("filledwaxcast", ((Integer)extrawax.metaMap.get("Filled Wax Cast")).intValue(), 13, "Filled Wax Cast");
            extrawax.add("waxcast_red", ((Integer)extrawax.metaMap.get("Refractory Cast")).intValue(), 30, "Refractory Cast");
            extrawax.add("wascastfilled_red", ((Integer)extrawax.metaMap.get("Filled Wax Cast_Red")).intValue(), 29, "Filled Wax Cast");
            extrawax.add("waxcastlava_red", ((Integer)extrawax.metaMap.get("Lava Cast")).intValue(), 28, "Lava Cast");
            extrawax.add("rodoffreezing", ((Integer)extrawax.metaMap.get("Rod of Freezing")).intValue(), 31, "Rod of Freezing", 1);
            extrawax.add("test", ((Integer)extrawax.metaMap.get("Test")).intValue(), 12, "Test (Ignore Me)");
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.STATIONARY_WATER, 1), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Filled Wax Cast")).intValue()), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Cast")).intValue()), false));
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.STATIONARY_LAVA, 1), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Lava Cast")).intValue()), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Refractory Cast")).intValue()), false));
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(Block.STATIONARY_WATER, 1), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Filled Wax Cast_Red")).intValue()), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Refractory Cast")).intValue()), false));
            thatch = new waxBlock(185, Material.CLAY, "Thatch");
            thatch.add("thatch", "Thatched Double Slab", 0, new Integer[] {Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(9)});
            waxBlockItem.names.put(Integer.valueOf(0), "Test 1");
            thatchslab = new waxSlab(181, Material.CLAY, "ThatchSlab");
            thatchslab.add("thatchslab", "Thatched Slab", 0, new Integer[] {Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(9)});
            waxSlabItem.names.put(Integer.valueOf(0), "Thatched Slab");
            this.recipes();
        }
    }

    protected void recipes()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("BluesWaxStuff_Enabled")))
        {
            ModLoader.addRecipe(new ItemStack(Block.TORCH, 2, 0), new Object[] {"XSX", "XBX", "XBX", 'S', Item.STRING, 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Block.TORCH, 2, 0), new Object[] {"SXX", "BXX", "BXX", 'S', Item.STRING, 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Block.TORCH, 2, 0), new Object[] {"XXS", "XXB", "XXB", 'S', Item.STRING, 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue()), new Object[] {"WWX", "WWX", "XXX", 'W', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue()), new Object[] {"XXX", "WWX", "WWX", 'W', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue()), new Object[] {"XWW", "XWW", "XXX", 'W', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue()), new Object[] {"XXX", "XWW", "XWW", 'W', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Block.BOOKSHELF, 1, 0), new Object[] {"PPP", "TTT", "PPP", 'P', Block.WOOD, 'T', new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Tablet")).intValue())});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Wax Cast")).intValue()), new Object[] {"WWW", "WXW", "WWW", 'W', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Refractory Cast")).intValue()), new Object[] {"WWW", "WXW", "WWW", 'W', ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Rod of Freezing")).intValue()), new Object[] {"GIG", "ISI", "BsB", 'G', Block.GLASS, 'I', Item.IRON_INGOT, 'S', Item.SNOW_BALL, 'B', new ItemStack(Item.INK_SACK, 1, 12), 's', Item.STICK});
            RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(Block.STATIONARY_LAVA.id, 1000), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Refractory Cast")).intValue()), new ItemStack(extrawax, 1, ((Integer)extrawax.metaMap.get("Lava Cast")).intValue()));
            Item[] var1 = new Item[] {Item.WHEAT, Item.SUGAR_CANE, ItemInterface.getItem("mulch").getItem()};
            byte var2 = 6;
            Item[] var3 = var1;
            int var4 = var1.length;

            for (int var5 = 0; var5 < var4; ++var5)
            {
                Item var6 = var3[var5];
                ModLoader.addRecipe(new ItemStack(thatchslab, var2, 0), new Object[] {"www", "iii", "www", 'w', ItemInterface.getItem("beeswax"), 'i', new ItemStack(var6)});
                ModLoader.addRecipe(new ItemStack(thatchslab, var2, 0), new Object[] {"www", "iii", "www", 'w', ItemInterface.getItem("refractoryWax"), 'i', new ItemStack(var6)});
            }

            ModLoader.addShapelessRecipe(new ItemStack(Block.STONE), new Object[] {ItemInterface.getItem("beeswax"), new ItemStack(Block.COBBLESTONE)});
            ModLoader.addShapelessRecipe(new ItemStack(Block.WATER_LILY), new Object[] {new ItemStack(Item.WHEAT), ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.BLAZE_ROD), new Object[] {"RGR", "PSP", "RGR", 'R', ItemInterface.getItem("refractoryWax"), 'G', new ItemStack(Item.GLOWSTONE_DUST), 'S', new ItemStack(Item.STICK), 'P', ItemInterface.getItem("phosphor")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"FXX", "SXX", "BXX", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"XFX", "XSX", "XBX", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"XXF", "XXS", "XXB", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("beeswax")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"FXX", "SXX", "BXX", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"XFX", "XSX", "XBX", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("refractoryWax")});
            ModLoader.addRecipe(new ItemStack(Item.ARROW), new Object[] {"XXF", "XXS", "XXB", 'F', new ItemStack(Item.FLINT), 'S', new ItemStack(Item.STICK), 'B', ItemInterface.getItem("refractoryWax")});
            ItemStack var9 = ItemInterface.getItem("beeswax");
            ItemStack[] var10 = new ItemStack[] {new ItemStack(Block.SAND), new ItemStack(Block.DIRT), new ItemStack(Block.GRAVEL)};
            ItemStack[] var11 = var10;
            int var12 = var10.length;
            int var7;
            ItemStack var8;

            for (var7 = 0; var7 < var12; ++var7)
            {
                var8 = var11[var7];
                ModLoader.addShapelessRecipe(new ItemStack(Item.CLAY_BALL, 4), new Object[] {var9, var8, var9, var8});
            }

            var9 = ItemInterface.getItem("refractoryWax");
            var11 = var10;
            var12 = var10.length;

            for (var7 = 0; var7 < var12; ++var7)
            {
                var8 = var11[var7];
                ModLoader.addShapelessRecipe(new ItemStack(Item.CLAY_BALL, 4), new Object[] {var9, var8, var9, var8});
            }

            var10 = new ItemStack[] {new ItemStack(Block.MOSSY_COBBLESTONE), new ItemStack(Block.SMOOTH_BRICK, 1, 1)};
            var11 = new ItemStack[] {new ItemStack(Block.COBBLESTONE), new ItemStack(Block.SMOOTH_BRICK)};

            for (var12 = 0; var12 < var10.length; ++var12)
            {
                ModLoader.addShapelessRecipe(var10[var12], new Object[] {ItemInterface.getItem("royalJelly"), var11[var12]});
            }

            ModLoader.addShapelessRecipe(new ItemStack(Block.SMOOTH_BRICK, 1, 2), new Object[] {ItemInterface.getItem("phosphor"), new ItemStack(Block.SMOOTH_BRICK)});
        }
    }
}
