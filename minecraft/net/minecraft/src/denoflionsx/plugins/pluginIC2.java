package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.addFermenterRecipes;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.ic2.api.Items;

public class pluginIC2 extends pluginBase {

    public static multiItem radioactive;

    public pluginIC2() {
        this.name = "pluginIC2";
        this.config = new Config(this.name + ".cfg");
        register();
    }

    @Override
    public void register() {
        if (!loaded) {
            this.defaults();
            this.runConfig();
            if (loaded = init()) {
                recipes();
            }
        }
    }

    @Override
    protected void defaults() {
        this.config.addDefault("LavaFromUranium=" + (1000000 / 20) * (100 - 20) / 100);
        this.config.addDefault("ChanceOfGoo=" + 10);
        this.config.addDefault("AmountOfFuelPerFermentation=" + 1000);
        this.config.addDefault("RadioactiveWaste_MJt=10");
        this.config.addDefault("RadioactiveWaste_BurnTime=" + 70000);
    }

    @Override
    protected boolean init() {
        if (denLib.convertToBoolean(core.config.getOption("pluginIc2_Enabled"))) {
            this.addItem("Uranium", Items.getItem("uraniumIngot"));
            this.addItem("Scrap", Items.getItem("scrap"));
            this.addBlock("Reinforced Stone", Items.getItem("reinforcedStone"));
            this.addBlock("Reinforced Glass", Items.getItem("reinforcedGlass"));
            this.addItem("Plates", Items.getItem("advancedAlloy"));
            radioactive = new multiItem(core.ItemIDs[6], "radioactivestuff");
            radioactive.metaMap.put("Radioactive Waste", 0);
            radioactive.metaMap.put("Radioactive Goo", 1);
            radioactive.metaMap.put("Containment Barrel", 2);
            radioactive.metaMap.put("Filled Containment Barrel", 3);
            radioactive.add("radioactivewaste", radioactive.metaMap.get("Radioactive Waste"), 10, "Radioactive Waste");
            radioactive.add("radioactivegoo", radioactive.metaMap.get("Radioactive Goo"), 10 + 16, "Radioactive Goo", true);
            radioactive.add("containmentbarrel", radioactive.metaMap.get("Containment Barrel"), 11, "Containment Barrel", 1);
            radioactive.add("filledcontainmentbarrel", radioactive.metaMap.get("Filled Containment Barrel"), 11 + 16, "Filled Containment Barrel", 1, true);
            LiquidManager.registerLiquidContainer(new LiquidContainer(new LiquidStack(radioactive.shiftedIndex, 1000), new ItemStack(radioactive, 1, radioactive.metaMap.get("Filled Containment Barrel")), new ItemStack(radioactive, 1, radioactive.metaMap.get("Containment Barrel")), true));
            this.hooked = true;
        }
        return this.hooked;
    }

    @Override
    protected void recipes() {
        int chance = Integer.valueOf(this.config.getOption("ChanceOfGoo"));
        int lava = Integer.valueOf(this.config.getOption("LavaFromUranium"));
        int amountper = Integer.valueOf(this.config.getOption("AmountOfFuelPerFermentation"));
        RecipeManagers.squeezerManager.addRecipe(100, new ItemStack[]{this.items.get("Uranium")}, new LiquidStack(Block.lavaStill, lava), new ItemStack(radioactive, 1, 1), chance);
        addFermenterRecipes.addNew(new ItemStack(radioactive, 1, radioactive.metaMap.get("Radioactive Goo")), amountper, radioactive);
        FuelManager.bronzeEngineFuel.put(radioactive.shiftedIndex, new EngineBronzeFuel(new ItemStack(radioactive, 1, radioactive.metaMap.get("Radioactive Waste")), this.getOptionInt("RadioactiveWaste_MJt"), this.getOptionInt("RadioactiveWaste_BurnTime"), 1));
        RecipeManagers.bottlerManager.addRecipe(10, new LiquidStack(radioactive, 1000), new ItemStack(radioactive, 1, radioactive.metaMap.get("Containment Barrel")), new ItemStack(radioactive, 1, radioactive.metaMap.get("Filled Containment Barrel")));
        ModLoader.addRecipe(new ItemStack(radioactive, 1, radioactive.metaMap.get("Containment Barrel")), new Object[]{
                    "XPX",
                    "PTP",
                    "XPX",
                    Character.valueOf('P'), this.items.get("Plates"),
                    Character.valueOf('T'), this.blocks.get("Reinforced Glass")});
    }
}
