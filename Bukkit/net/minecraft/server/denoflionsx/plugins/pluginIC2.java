package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import ic2.api.Items;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginIC2 extends pluginBase
{
    public static multiItem radioactive;

    public pluginIC2()
    {
        this.name = "pluginIC2";
        this.mod = "mod_IC2";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();
            this.runConfig();

            if (this.loaded = this.init())
            {
                this.recipes();

                if (this.hooked)
                {
                    core.print(this.name + " loaded!");
                }
            }
        }
    }

    protected void defaults()
    {
        this.config.addDefault("RadioactiveWaste_ItemID=" + core.ItemIDs[6]);
        this.config.addDefault("LavaFromUranium=40000");
        this.config.addDefault("ChanceOfGoo=10");
        this.config.addDefault("AmountOfFuelPerFermentation=1000");
        this.config.addDefault("RadioactiveWaste_MJt=10");
        this.config.addDefault("RadioactiveWaste_BurnTime=70000");
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            return false;
        }
        else
        {
            if (denLib.convertToBoolean(core.config.getOption("pluginIc2_Enabled")))
            {
                this.addItem("Uranium", Items.getItem("uraniumIngot"));
                this.addItem("Scrap", Items.getItem("scrap"));
                this.addBlock("Reinforced Stone", Items.getItem("reinforcedStone"));
                this.addBlock("Reinforced Glass", Items.getItem("reinforcedGlass"));
                this.addItem("Plates", Items.getItem("advancedAlloy"));
                radioactive = new multiItem(this.getOptionInt("RadioactiveWaste_ItemID").intValue(), "radioactivestuff");
                radioactive.metaMap.put("Radioactive Waste", Integer.valueOf(0));
                radioactive.metaMap.put("Radioactive Goo", Integer.valueOf(1));
                radioactive.metaMap.put("Containment Barrel", Integer.valueOf(2));
                radioactive.metaMap.put("Filled Containment Barrel", Integer.valueOf(3));
                radioactive.add("radioactivewaste", ((Integer)radioactive.metaMap.get("Radioactive Waste")).intValue(), 10, "Radioactive Waste");
                radioactive.add("radioactivegoo", ((Integer)radioactive.metaMap.get("Radioactive Goo")).intValue(), 26, "Radioactive Goo", true);
                radioactive.add("containmentbarrel", ((Integer)radioactive.metaMap.get("Containment Barrel")).intValue(), 11, "Containment Barrel", 1);
                radioactive.add("filledcontainmentbarrel", ((Integer)radioactive.metaMap.get("Filled Containment Barrel")).intValue(), 27, "Filled Containment Barrel", 1, true);
                LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(radioactive.id, 1000), new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Filled Containment Barrel")).intValue()), new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Containment Barrel")).intValue()), true));
                this.hooked = true;
            }

            return this.hooked;
        }
    }

    protected void recipes()
    {
        int var1 = Integer.valueOf(this.config.getOption("ChanceOfGoo")).intValue();
        int var2 = Integer.valueOf(this.config.getOption("LavaFromUranium")).intValue();
        int var3 = Integer.valueOf(this.config.getOption("AmountOfFuelPerFermentation")).intValue();
        RecipeManagers.squeezerManager.addRecipe(100, new ItemStack[] {(ItemStack)this.items.get("Uranium")}, new LiquidStack(Block.STATIONARY_LAVA, var2), new ItemStack(radioactive, 1, 1), var1);
        addFermenterRecipes.addNew(new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Radioactive Goo")).intValue()), var3, radioactive);
        FuelManager.bronzeEngineFuel.put(Integer.valueOf(radioactive.id), new EngineBronzeFuel(new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Radioactive Waste")).intValue()), this.getOptionInt("RadioactiveWaste_MJt").intValue(), this.getOptionInt("RadioactiveWaste_BurnTime").intValue(), 1));
        RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(radioactive, 1000), new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Containment Barrel")).intValue()), new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Filled Containment Barrel")).intValue()));
        ModLoader.addRecipe(new ItemStack(radioactive, 1, ((Integer)radioactive.metaMap.get("Containment Barrel")).intValue()), new Object[] {"XPX", "PTP", "XPX", 'P', this.items.get("Plates"), 'T', this.blocks.get("Reinforced Glass")});
    }
}
