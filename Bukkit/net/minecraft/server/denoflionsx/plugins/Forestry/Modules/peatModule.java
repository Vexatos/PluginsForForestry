package net.minecraft.server.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.Forestry.LiquidContainerSystem;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class peatModule extends baseModule
{
    public static multiItem liquidpeat;
    public static multiItem sugarypeat;

    public peatModule(pluginBase var1)
    {
        super(var1);
    }

    protected void defaults()
    {
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
        this.parent.config.addDefault("SugaryPeat_Liquid_BurnTime=" + String.valueOf(40000));
        this.parent.config.addDefault("SugaryPeat_Carpenter_Bonus=1");
    }

    public static void load(pluginBase var0)
    {
        peatModule var1 = new peatModule(var0);
        var1.register();
    }

    protected void recipes()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled")))
        {
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {ItemInterface.getItem("peat")}, new LiquidStack((new ItemStack(liquidpeat, 1, 0)).id, Integer.valueOf(this.parent.config.getOption("LiquidPeat_AmountPerSqueeze")).intValue(), 0), ItemInterface.getItem("ash"), Integer.valueOf(this.parent.config.getOption("LiquidPeat_PercentChanceOfAsh")).intValue());
            FuelManager.bronzeEngineFuel.put(Integer.valueOf((new ItemStack(liquidpeat, 1, 0)).id), new EngineBronzeFuel(new ItemStack(liquidpeat, 1, 0), Integer.valueOf(this.parent.config.getOption("LiquidPeat_MJt")).intValue(), Integer.valueOf(this.parent.config.getOption("LiquidPeat_BurnTime")).intValue(), 1));
        }

        if (denLib.convertToBoolean(this.parent.config.getOption("StillFix")))
        {
            RecipeManagers.stillManager.addRecipe(Integer.valueOf(this.parent.config.getOption("Still_WorkCycles")).intValue(), new LiquidStack(ItemInterface.getItem("liquidBiomass").getItem(), Integer.valueOf(this.parent.config.getOption("Still_BiomassPerCast")).intValue()), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), Integer.valueOf(this.parent.config.getOption("Still_BiofuelPerCast")).intValue()));
        }

        if (denLib.convertToBoolean(this.parent.config.getOption("BiofuelInBiogas")))
        {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(ItemInterface.getItem("liquidBiofuel").id), new EngineBronzeFuel(ItemInterface.getItem("liquidBiofuel"), Integer.valueOf(this.parent.config.getOption("BiofuelMJt")).intValue(), Integer.valueOf(this.parent.config.getOption("BiofuelBurnTime")).intValue(), 1));
        }

        LiquidContainerSystem.create(liquidpeat);

        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled")))
        {
            ItemStack var1 = new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue());
            ItemStack var2 = new ItemStack(sugarypeat, Integer.valueOf(this.parent.config.getOption("SugaryPeat_Carpenter_Bonus")).intValue() + 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue());
            ArrayList var3 = new ArrayList();
            var3.add(new Object[] {"SPS", "PCP", "SPS", 'S', Item.SUGAR, 'P', ItemInterface.getItem("peat"), 'C', new ItemStack(Item.INK_SACK, 1, 3)});
            var3.add(new Object[] {"SPS", "PBP", "SPS", 'S', Item.SUGAR, 'P', ItemInterface.getItem("peat"), 'B', ItemInterface.getItem("beeswax")});
            var3.add(new Object[] {"SPS", "PMP", "SPS", 'S', Item.SUGAR, 'P', ItemInterface.getItem("peat"), 'M', ItemInterface.getItem("mulch")});
            Iterator var4 = var3.iterator();

            while (var4.hasNext())
            {
                Object[] var5 = (Object[])var4.next();
                ModLoader.addRecipe(var1, var5);
                RecipeManagers.carpenterManager.addRecipe(10, new LiquidStack(liquidpeat, 250), (ItemStack)null, var2, var5);
            }

            FuelManager.copperEngineFuel.put(Integer.valueOf((new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue())).id), new EngineCopperFuel(new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue()), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Item_MJt")).intValue(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Item_BurnTime")).intValue()));
            RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue())}, new LiquidStack(sugarypeat, Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_AmountPerSqueeze")).intValue()));
            FuelManager.bronzeEngineFuel.put(Integer.valueOf((new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue())).id), new EngineBronzeFuel(new ItemStack(sugarypeat, 1, ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue()), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_MJt")).intValue(), Integer.valueOf(this.parent.config.getOption("SugaryPeat_Liquid_BurnTime")).intValue(), 1));
            LiquidContainerSystem.create(sugarypeat);
        }
    }

    protected void init()
    {
        if (denLib.convertToBoolean(this.parent.config.getOption("LiquidPeat_Enabled")))
        {
            liquidpeat = new multiItem(Integer.valueOf(this.parent.config.getOption("LiquidPeat_ItemID")).intValue(), "liquidpeat");
            liquidpeat.metaMap.put("Liquid Peat", Integer.valueOf(0));
            liquidpeat.metaMap.put("Peat Capsule", Integer.valueOf(1));
            liquidpeat.metaMap.put("Peat Can", Integer.valueOf(2));
            liquidpeat.metaMap.put("Peat Capsule_Red", Integer.valueOf(3));
            liquidpeat.metaMap.put("Peat Bucket", Integer.valueOf(4));
            liquidpeat.metaMap.put("Peat Bottle", Integer.valueOf(5));
            liquidpeat.metaMap.put("Peat Cell", Integer.valueOf(6));
            liquidpeat.add("liquidpeat", ((Integer)liquidpeat.metaMap.get("Liquid Peat")).intValue(), 0, "Liquid Peat");
            liquidpeat.add("peatcap", ((Integer)liquidpeat.metaMap.get("Peat Capsule")).intValue(), 32, "Peat Capsule");
            liquidpeat.add("peatcan", ((Integer)liquidpeat.metaMap.get("Peat Can")).intValue(), 16, "Peat Can");
            liquidpeat.add("peatcap_red", ((Integer)liquidpeat.metaMap.get("Peat Capsule_Red")).intValue(), 48, "Peat Capsule");
            liquidpeat.add("peatbucket", ((Integer)liquidpeat.metaMap.get("Peat Bucket")).intValue(), 18, "Peat Bucket", 1, Item.BUCKET);
            liquidpeat.add("peatbottle", ((Integer)liquidpeat.metaMap.get("Peat Bottle")).intValue(), 21, "Peat Bottle");
        }

        if (denLib.convertToBoolean(this.parent.config.getOption("SugaryPeat_Enabled")))
        {
            sugarypeat = new multiItem(Integer.valueOf(this.parent.config.getOption("SugaryPeat_ItemID")).intValue(), "liquidsugarypeat");
            sugarypeat.metaMap.put("Liquid Sugary Peat", Integer.valueOf(0));
            sugarypeat.metaMap.put("Sugary Peat Capsule", Integer.valueOf(1));
            sugarypeat.metaMap.put("Sugary Peat Can", Integer.valueOf(2));
            sugarypeat.metaMap.put("Sugary Peat Capsule_Red", Integer.valueOf(3));
            sugarypeat.metaMap.put("Sugary Peat Bucket", Integer.valueOf(4));
            sugarypeat.metaMap.put("Sugary Peat Bottle", Integer.valueOf(5));
            sugarypeat.metaMap.put("Sugary Peat Cell", Integer.valueOf(6));
            sugarypeat.metaMap.put("Sugary Peat", Integer.valueOf(7));
            sugarypeat.add("liquidsugarypeat", ((Integer)sugarypeat.metaMap.get("Liquid Sugary Peat")).intValue(), 5, "Sugary Peat");
            sugarypeat.add("sugarypeatcap", ((Integer)sugarypeat.metaMap.get("Sugary Peat Capsule")).intValue(), 54, "Sugary Peat Capsule");
            sugarypeat.add("sugarypeatcan", ((Integer)sugarypeat.metaMap.get("Sugary Peat Can")).intValue(), 38, "Sugary Peat Can");
            sugarypeat.add("sugarypeatcap_red", ((Integer)sugarypeat.metaMap.get("Sugary Peat Capsule_Red")).intValue(), 70, "Sugary Peat Capsule");
            sugarypeat.add("sugarypeatbucket", ((Integer)sugarypeat.metaMap.get("Sugary Peat Bucket")).intValue(), 50, "Sugary Peat Bucket", 1);
            sugarypeat.add("sugarypeatbottle", ((Integer)sugarypeat.metaMap.get("Sugary Peat Bottle")).intValue(), 22, "Sugary Peat Bottle");
            sugarypeat.add("sugarypeat", ((Integer)sugarypeat.metaMap.get("Sugary Peat")).intValue(), 6, "Sugary Peat");
        }

        this.recipes();
    }
}
