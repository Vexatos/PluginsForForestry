package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;

public class peatModule extends baseModule {

    public static multiItem liquidpeat;
    public static multiItem sugarypeat;

    public peatModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        this.parent.config.addDefault("# These options are for Liquid Peat");
        this.parent.config.addDefault("LiquidPeat_Enabled=true");
        this.parent.config.addDefault("LiquidPeat_ItemID=5312");
        this.parent.config.addDefault("LiquidPeat_MJt=1");
        this.parent.config.addDefault("LiquidPeat_BurnTime=20000");
        this.parent.config.addDefault("LiquidPeat_AmountPerSqueeze=250");
        this.parent.config.addDefault("LiquidPeat_PercentChanceOfAsh=40");
        this.parent.config.addDefault("SugaryPeat_Enabled=true");
        this.parent.config.addDefault("SugaryPeat_ItemID=5316");
        this.parent.config.addDefault("SugaryPeat_Item_MJt=1");
        this.parent.config.addDefault("SugaryPeat_Item_BurnTime=20000");
        this.parent.config.addDefault("SugaryPeat_Liquid_AmountPerSqueeze=250");
        this.parent.config.addDefault("SugaryPeat_Liquid_MJt=2");
        this.parent.config.addDefault("SugaryPeat_Liquid_BurnTime=" + String.valueOf(((80000 + (80000 * (50 / 100))) / 2)));
        this.parent.config.addDefault("SugaryPeat_Carpenter_Bonus=1");
    }

    public static void load(pluginBase parent) {
        baseModule b = new peatModule(parent);
        b.register();
    }

    @Override
    protected void recipes() {
        //----------------------------
        // LIQUID PEAT STUFF
        //----------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled"))) {
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{ItemInterface.getItem("peat")}, new LiquidStack(new ItemStack(liquidpeat, 1, 0).itemID, Integer.valueOf(this.parent.config.getOption("LiquidPeat_AmountPerSqueeze")), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.parent.config.getOption("LiquidPeat_PercentChanceOfAsh")));
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(new ItemStack(liquidpeat, 1, 0).itemID), new EngineBronzeFuel(new ItemStack(liquidpeat, 1, 0), Integer.valueOf(this.parent.config.getOption("LiquidPeat_MJt")), Integer.valueOf(this.parent.config.getOption("LiquidPeat_BurnTime")), 1));
        }
        if (denLib.convertToBoolean(this.parent.config.getOption("StillFix"))) {
            RecipeManagers.stillManager.addRecipe(Integer.valueOf(this.parent.config.getOption("Still_WorkCycles")), new LiquidStack(ItemInterface.getItem("liquidBiomass").getItem(), Integer.valueOf(this.parent.config.getOption("Still_BiomassPerCast"))), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), Integer.valueOf(this.parent.config.getOption("Still_BiofuelPerCast"))));
        }
        if (denLib.convertToBoolean(this.parent.config.getOption("BiofuelInBiogas"))) {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(ItemInterface.getItem("liquidBiofuel").itemID), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.parent.config.getOption("BiofuelMJt")), Integer.valueOf(this.parent.config.getOption("BiofuelBurnTime")), 1));
        }
        LiquidContainerSystem.create(liquidpeat);
        //--------------------------
        // SUGARY PEAT STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            ItemStack Speat = new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat"));
            ItemStack SpeatBonus = new ItemStack(sugarypeat, Integer.valueOf(this.parent.config.getOption("SugaryPeat_Carpenter_Bonus")) + 1, sugarypeat.metaMap.get("Sugary Peat"));
            ArrayList<Object[]> recipes = new ArrayList();
            recipes.add(new Object[]{
                        "SPS",
                        "PCP",
                        "SPS",
                        Character.valueOf('S'), Item.sugar,
                        Character.valueOf('P'), ItemInterface.getItem("peat"),
                        Character.valueOf('C'), new ItemStack(Item.dyePowder, 1, 3)});
            recipes.add(new Object[]{
                        "SPS",
                        "PBP",
                        "SPS",
                        Character.valueOf('S'), Item.sugar,
                        Character.valueOf('P'), ItemInterface.getItem("peat"),
                        Character.valueOf('B'), ItemInterface.getItem("beeswax")});
            recipes.add(new Object[]{
                        "SPS",
                        "PMP",
                        "SPS",
                        Character.valueOf('S'), Item.sugar,
                        Character.valueOf('P'), ItemInterface.getItem("peat"),
                        Character.valueOf('M'), ItemInterface.getItem("mulch")});
            for (Object[] o : recipes) {
                ModLoader.addRecipe(Speat, o);
                RecipeManagers.carpenterManager.addRecipe(10, new LiquidStack(liquidpeat, (1000 / 4)), null, SpeatBonus, o);
            }
            FuelManager.copperEngineFuel.put(new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat")).itemID, new EngineCopperFuel(new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat")), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Item_MJt")), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Item_BurnTime"))));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat"))}, new LiquidStack(sugarypeat, Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_AmountPerSqueeze"))));
            FuelManager.bronzeEngineFuel.put(new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat")).itemID, new EngineBronzeFuel(new ItemStack(sugarypeat, 1, sugarypeat.metaMap.get("Sugary Peat")), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_MJt")), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_BurnTime")), 1));
            LiquidContainerSystem.create(sugarypeat);
        }
    }

    @Override
    protected void init() {
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled"))) {
            //--------------------------------------------
            liquidpeat = new multiItem(Integer.valueOf(this.parent.config.getOption("LiquidPeat_ItemID")), "liquidpeat");
            liquidpeat.metaMap.put("Liquid Peat", 0);
            liquidpeat.metaMap.put("Peat Capsule", 1);
            liquidpeat.metaMap.put("Peat Can", 2);
            liquidpeat.metaMap.put("Peat Capsule_Red", 3);
            liquidpeat.metaMap.put("Peat Bucket", 4);
            liquidpeat.metaMap.put("Peat Bottle", 5);
            liquidpeat.metaMap.put("Peat Cell", 6);
            //---------------------------------------------
            liquidpeat.add("liquidpeat", liquidpeat.metaMap.get("Liquid Peat"), 0, "Liquid Peat");
            liquidpeat.add("peatcap", liquidpeat.metaMap.get("Peat Capsule"), 32, "Peat Capsule");
            liquidpeat.add("peatcan", liquidpeat.metaMap.get("Peat Can"), 16, "Peat Can");
            liquidpeat.add("peatcap_red", liquidpeat.metaMap.get("Peat Capsule_Red"), 48, "Peat Capsule");
            liquidpeat.add("peatbucket", liquidpeat.metaMap.get("Peat Bucket"), 16 + 2, "Peat Bucket", 1, Item.bucketEmpty);
            liquidpeat.add("peatbottle", liquidpeat.metaMap.get("Peat Bottle"), 5 + 16, "Peat Bottle");
        }
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            sugarypeat = new multiItem(Integer.valueOf(this.parent.config.getOption("SugaryPeat_ItemID")), "liquidsugarypeat");
            sugarypeat.metaMap.put("Liquid Sugary Peat", 0);
            sugarypeat.metaMap.put("Sugary Peat Capsule", 1);
            sugarypeat.metaMap.put("Sugary Peat Can", 2);
            sugarypeat.metaMap.put("Sugary Peat Capsule_Red", 3);
            sugarypeat.metaMap.put("Sugary Peat Bucket", 4);
            sugarypeat.metaMap.put("Sugary Peat Bottle", 5);
            sugarypeat.metaMap.put("Sugary Peat Cell", 6);
            sugarypeat.metaMap.put("Sugary Peat", 7);
            //-----------------------------------------------
            sugarypeat.add("liquidsugarypeat", sugarypeat.metaMap.get("Liquid Sugary Peat"), 5, "Sugary Peat");
            sugarypeat.add("sugarypeatcap", sugarypeat.metaMap.get("Sugary Peat Capsule"), 20 + 16 + 16 + 2, "Sugary Peat Capsule");
            sugarypeat.add("sugarypeatcan", sugarypeat.metaMap.get("Sugary Peat Can"), 20 + 16 + 2, "Sugary Peat Can");
            sugarypeat.add("sugarypeatcap_red", sugarypeat.metaMap.get("Sugary Peat Capsule_Red"), 20 + 16 + 2 + 32, "Sugary Peat Capsule");
            sugarypeat.add("sugarypeatbucket", sugarypeat.metaMap.get("Sugary Peat Bucket"), 20 + 16 + 16 - 2, "Sugary Peat Bucket", 1);
            sugarypeat.add("sugarypeatbottle", sugarypeat.metaMap.get("Sugary Peat Bottle"), 20 + 2, "Sugary Peat Bottle");
            sugarypeat.add("sugarypeat", sugarypeat.metaMap.get("Sugary Peat"), 6, "Sugary Peat");
        }
        recipes();
    }
}
