package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomProvider;
import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomSeedProvider;
import net.minecraft.server.denoflionsx.plugins.BetterFarming.growHook;
import net.minecraft.server.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.server.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginBetterFarming extends pluginBase
{
    public static multiItem citrusJuice;
    protected String Class = "";

    public pluginBetterFarming()
    {
        this.mod = "mod_BetterFarming";
        this.name = "pluginBetterFarming";
        this.Class = this.mod;
        this.config = new Config("pluginBetterFarming.cfg");

        if (core.isBukkit)
        {
            this.Class = "net.minecraft.server." + this.Class;
        }

        this.register();
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            core.print(this.mod + " not found!");
            return this.hooked;
        }
        else
        {
            String var1 = "BuildCraftTransport";

            if (core.isBukkit)
            {
                var1 = "net.minecraft.server." + var1;
            }

            this.addItem(this.Class, "appleSeeds", "Apple Seeds", 0);
            this.addItem(this.Class, "orangeSeeds", "Orange Seeds", 0);
            this.addItem(this.Class, "cocoaSeeds", "Cocoa Seeds", 0);
            this.addItem(this.Class, "mintSeeds", "Mint Seeds", 0);
            this.addItem(this.Class, "lemonSeeds", "Lemon Seeds", 0);
            this.addItem(this.Class, "orange", "Orange", 0);
            this.addItem(this.Class, "lemon", "Lemon", 0);
            this.addItem(this.Class, "mint", "Mint", 0);
            this.addItem(this.Class, "milkBottle", "Milk Bottle", 0);
            this.addBlock(this.Class, "cocoaTree", "Cocoa Tree", 0);
            this.addBlock(this.Class, "appleTree", "Apple Tree", 0);
            this.addBlock(this.Class, "orangeTree", "Orange Tree", 0);
            this.addBlock(this.Class, "lemonTree", "Lemon Tree", 0);
            this.addBlock(this.Class, "mintCrop", "Mint Crop", 0);
            this.addItem(var1, "pipeWaterproof", "Waterproofing", 0);

            if (denLib.convertToBoolean(this.config.getOption("CitrusJuice_Enabled")))
            {
                citrusJuice = new multiItem(Integer.valueOf(this.config.getOption("CitrusJuice_ItemID")).intValue(), "citrusjuice");
                citrusJuice.metaMap.put("Citrus Juice", Integer.valueOf(0));
                citrusJuice.metaMap.put("Citrus Capsule", Integer.valueOf(1));
                citrusJuice.metaMap.put("Citrus Can", Integer.valueOf(2));
                citrusJuice.metaMap.put("Citrus Capsule_Red", Integer.valueOf(3));
                citrusJuice.metaMap.put("Citrus Bucket", Integer.valueOf(4));
                citrusJuice.metaMap.put("Citrus Bottle", Integer.valueOf(5));
                citrusJuice.add("citrusjuice", ((Integer)citrusJuice.metaMap.get("Citrus Juice")).intValue(), 3, "Citrus Juice");
                citrusJuice.add("citruscap", ((Integer)citrusJuice.metaMap.get("Citrus Capsule")).intValue(), 35, "Citrus Capsule");
                citrusJuice.add("citruscan", ((Integer)citrusJuice.metaMap.get("Citrus Can")).intValue(), 19, "Citrus Can");
                citrusJuice.add("citruscap_red", ((Integer)citrusJuice.metaMap.get("Citrus Capsule_Red")).intValue(), 51, "Citrus Capsule");
                citrusJuice.add("citrusbucket", ((Integer)citrusJuice.metaMap.get("Citrus Bucket")).intValue(), 34, "Citrus Bucket", 1);
                citrusJuice.add("citrusbottle", ((Integer)citrusJuice.metaMap.get("Citrus Bottle")).intValue(), 53, "Citrus Bottle");
            }

            this.hooked = true;

            if (this.hooked)
            {
                core.print(this.getName() + " Loaded!");
            }

            return this.hooked;
        }
    }

    protected void recipes()
    {
        try
        {
            int var1 = Integer.valueOf(this.config.getOption("Seeds_SeedOilPerSqueeze")).intValue();
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Apple Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, var1));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Orange Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, var1));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Cocoa Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, var1));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Mint Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, var1));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Lemon Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, var1));

            if (denLib.convertToBoolean(this.config.getOption("CitrusJuice_Enabled")))
            {
                int var2 = Integer.valueOf(this.config.getOption("CitrusJuice_AmountPerSqueeze")).intValue();
                int var3 = Integer.valueOf(this.config.getOption("CitrusJuice_PercentChanceOfMulch")).intValue();
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(((ItemStack)this.items.get("Lemon")).getItem())}, new LiquidStack((new ItemStack(citrusJuice, 1, 0)).id, var2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), var3);
                RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(((ItemStack)this.items.get("Orange")).getItem())}, new LiquidStack((new ItemStack(citrusJuice, 1, 0)).id, var2, 0), new ItemStack(ItemInterface.getItem("mulch").getItem()), var3);
                addFermenterRecipes.add(new ItemStack(citrusJuice), 1.5F);
                FuelManager.bronzeEngineFuel.put(Integer.valueOf(citrusJuice.id), new EngineBronzeFuel(new ItemStack(citrusJuice, 1, ((Integer)citrusJuice.metaMap.get("Citrus Juice")).intValue()), this.getOptionInt("CitrusJuice_MJt").intValue(), this.getOptionInt("CitrusJuice_BurnTime").intValue(), 1));
            }

            if (denLib.convertToBoolean(this.config.getOption("ForestryIntegration")))
            {
                growHook.engage();
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Cocoa Tree"), new ItemStack(Item.INK_SACK, 1, 3), new int[] {((ItemStack)this.items.get("Cocoa Seeds")).id, (new ItemStack(Item.INK_SACK, 1, 3)).id}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Apple Tree"), new ItemStack(Item.APPLE, 1, 0), new int[] {((ItemStack)this.items.get("Apple Seeds")).id, Item.APPLE.id}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Orange Tree"), new ItemStack(((ItemStack)this.items.get("Orange")).getItem(), 1, 0), new int[] {((ItemStack)this.items.get("Orange Seeds")).id, ((ItemStack)this.items.get("Orange")).id}));
                CropProviders.arborealCrops.add(new cropCustomProvider(this.getBlock("Lemon Tree"), new ItemStack(((ItemStack)this.items.get("Lemon")).getItem(), 1, 0), new int[] {((ItemStack)this.items.get("Lemon Seeds")).id, ((ItemStack)this.items.get("Lemon")).id}));
                CropProviders.cerealCrops.add(new cropCustomSeedProvider(((ItemStack)this.items.get("Mint")).getItem(), ((ItemStack)this.items.get("Mint Seeds")).getItem(), this.getBlock("Mint Crop"), 5));
            }

            ModLoader.addRecipe((ItemStack)this.items.get("Waterproofing"), new Object[] {"XXX", "XMX", "XXX", 'M', this.items.get("Mint")});
            addFermenterRecipes.addItem(((ItemStack)this.items.get("Mint")).getItem(), 150, this);
            LiquidContainerSystem.create(citrusJuice);
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();

            if (this.loaded = this.init())
            {
                this.recipes();
            }
        }
    }

    protected void defaults()
    {
        this.config.addDefault("[BetterFarming Options]");
        this.config.addDefault("Seeds_SeedOilPerSqueeze=100");
        this.config.addDefault("# This option makes BetterFarming plants work in the Forestry Arboretum and Logger");
        this.config.addDefault("ForestryIntegration=true");
        this.config.addDefault("# These options are for Citrus Juice");
        this.config.addDefault("# Citrus Juice is made with BetterFarming fruit in a squeezer");
        this.config.addDefault("CitrusJuice_Enabled=true");
        this.config.addDefault("CitrusJuice_ItemID=5313");
        this.config.addDefault("CitrusJuice_AmountPerSqueeze=200");
        this.config.addDefault("CitrusJuice_PercentChanceOfMulch=40");
        this.config.addDefault("CitrusJuice_MJt=1");
        this.config.addDefault("CitrusJuice_BurnTime=10000");
        this.config.writeConfig();
        this.config.readFile();
    }
}
