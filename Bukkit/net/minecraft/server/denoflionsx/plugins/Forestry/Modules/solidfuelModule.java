package net.minecraft.server.denoflionsx.plugins.Forestry.Modules;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.Forestry.Modules.solidfuel.barObject;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.server.ItemStack;

public class solidfuelModule extends baseModule
{
    public static multiItem solidfuel;
    private boolean isShiny = false;
    private String suffix;

    public solidfuelModule(pluginBase var1)
    {
        super(var1);
    }

    public static void load(pluginBase var0)
    {
        solidfuelModule var1 = new solidfuelModule(var0);
        var1.register();
    }

    protected void defaults()
    {
        this.addDefault("# These options are for the Solid Fuel Module");
        this.addDefault("solidfuelModule_Enabled=true");
        this.addDefault("solidfuel_ItemID=" + String.valueOf(core.ItemIDs[7]));
        this.addDefault("solidfuel_InternalName=solidfuel");
        this.addDefault("solidfuel_isShiny=true");
        this.addDefault("solidfuel_Suffix=Infused Bar");
        this.addDefault("SolidBiofuel_MJt=5");
        this.addDefault("SolidBiofuel_BurnTime=" + String.valueOf(40000));
    }

    protected void init()
    {
        if (this.getOptionBool("solidfuelModule_Enabled"))
        {
            this.isShiny = this.getOptionBool("solidfuel_isShiny");
            this.suffix = " " + this.getOption("solidfuel_Suffix");
            solidfuel = new multiItem(this.getOptionInt("solidfuel_ItemID").intValue(), this.getOption("solidfuel_InternalName"));
            solidfuel.metaMap.put("RESERVED DO NOT USE", Integer.valueOf(0));
            solidfuel.metaMap.put("Solid Biofuel", Integer.valueOf(1));
            solidfuel.add("solidbiofuelbar", ((Integer)solidfuel.metaMap.get("Solid Biofuel")).intValue(), 25, "Biofuel" + this.suffix, this.isShiny);
        }

        this.recipes();
    }

    protected void recipes()
    {
        if (this.getOptionBool("solidfuelModule_Enabled"))
        {
            this.infusedBarRecipes();
            this.fuelRecipes();
        }
    }

    private void infusedBarRecipes()
    {
        ArrayList var1 = new ArrayList();
        var1.add(new barObject(new ItemStack(solidfuel, 1, ((Integer)solidfuel.metaMap.get("Solid Biofuel")).intValue()), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), 1000), ItemInterface.getItem("mulch")));
        Iterator var2 = var1.iterator();

        while (var2.hasNext())
        {
            barObject var3 = (barObject)var2.next();

            if (var3 != null)
            {
                this.addInfusedRecipe(var3);
            }
        }
    }

    private void addInfusedRecipe(barObject var1)
    {
        ArrayList var3 = new ArrayList();
        var3.add(ItemInterface.getItem("propolis"));
        var3.add(ItemInterface.getItem("pollen"));
        ArrayList var4 = this.makeRecipes(var1, var3);
        ItemStack var2;

        if (this.getOptionBool("SugaryPeat_Enabled"))
        {
            var2 = new ItemStack(peatModule.sugarypeat, 1, ((Integer)peatModule.sugarypeat.metaMap.get("Sugary Peat")).intValue());
        }
        else
        {
            var2 = ItemInterface.getItem("peat");
        }

        Iterator var5 = var4.iterator();

        while (var5.hasNext())
        {
            Object[] var6 = (Object[])var5.next();
            RecipeManagers.carpenterManager.addRecipe(10, var1.getLiquidStack(), var2, var1.getItemStack(), var6);
        }
    }

    private void fuelRecipes()
    {
        ItemStack var1 = new ItemStack(solidfuel, 1, ((Integer)solidfuel.metaMap.get("Solid Biofuel")).intValue());
        int var2 = this.getOptionInt("SolidBiofuel_BurnTime").intValue();
        int var3 = this.getOptionInt("SolidBiofuel_MJt").intValue();
        FuelManager.copperEngineFuel.put(Integer.valueOf(solidfuel.id), new EngineCopperFuel(var1, var3, var2));
    }

    private ArrayList makeRecipes(barObject var1, ArrayList var2)
    {
        ArrayList var3 = new ArrayList();
        Iterator var4 = var2.iterator();

        while (var4.hasNext())
        {
            ItemStack var5 = (ItemStack)var4.next();
            var3.add(new Object[] {"MMM", "RPR", "MMM", 'M', var1.getCatalyst(), 'P', var5, 'R', ItemInterface.getItem("royalJelly")});
        }

        return var3;
    }
}
