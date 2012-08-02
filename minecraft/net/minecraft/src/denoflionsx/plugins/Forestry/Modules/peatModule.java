package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import java.util.ArrayList;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.denLib.denLib;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.denoflionsx.API.API;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Colors;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuel;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.newFuels.customFuelSolid;

public class peatModule extends baseModule {

    public static customFuel liquidpeat;
    public static customFuel sugarypeat;
    public static customFuelSolid sugarypeatsolid;

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
        this.parent.config.addDefault("SugaryPeat_Liquid_ItemID=5316");
        this.parent.config.addDefault("SugaryPeat_Item_ItemID=" + core.ItemIDs_New.get(11));
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
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{ItemInterface.getItem("peat")}, new LiquidStack(API.getItem("liquidpeat").itemID, Integer.valueOf(this.parent.config.getOption("LiquidPeat_AmountPerSqueeze")), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.parent.config.getOption("LiquidPeat_PercentChanceOfAsh")));
        }
        //--------------------------
        // SUGARY PEAT STUFF
        //--------------------------
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            ItemStack Speat = API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar"));
            ItemStack SpeatBonus = new ItemStack(API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar")).getItem(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Carpenter_Bonus")) + 1, API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar")).getItemDamage());
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
                RecipeManagers.carpenterManager.addRecipe(10, new LiquidStack(API.getItem("liquidpeat").getItem(), (1000 / 4)), null, SpeatBonus, o);
            }
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar"))}, new LiquidStack(API.getItem(denLib.toLowerCaseNoSpaces("Sugary Peat Bar")).getItem(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_AmountPerSqueeze"))));
        }
    }

    @Override
    protected void init() {
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled"))) {
            //--------------------------------------------
            liquidpeat = new customFuel("Liquid Peat", this.getOptionInt("LiquidPeat_MJt"), this.getOptionInt("LiquidPeat_BurnTime"), customFuel.populateSprites(2), this.getOptionInt("LiquidPeat_ItemID"), Colors.Values.BROWN.getColor());
        }
        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled"))) {
            sugarypeat = new customFuel("Sugary Peat", this.getOptionInt("SugaryPeat_Liquid_MJt"), this.getOptionInt("SugaryPeat_Liquid_BurnTime"), customFuel.populateSprites(3), this.getOptionInt("SugaryPeat_Liquid_ItemID"), Colors.Values.LIGHTBROWN.getColor());
            sugarypeatsolid = new customFuelSolid(this.getOptionInt("SugaryPeat_Item_ItemID"), "Sugary Peat Bar", this.getOptionInt("SugaryPeat_Item_MJt"), this.getOptionInt("SugaryPeat_Item_BurnTime"), Colors.shiftRow(2, 6), false);
        }
        recipes();
    }
}
