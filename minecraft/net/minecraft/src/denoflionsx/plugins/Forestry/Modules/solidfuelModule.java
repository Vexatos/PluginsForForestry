package net.minecraft.src.denoflionsx.plugins.Forestry.Modules;

import java.util.ArrayList;
import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.item_templates.multiItem;
import net.minecraft.src.denoflionsx.plugins.Forestry.Modules.solidfuel.barObject;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.core.ItemInterface;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;

public class solidfuelModule extends baseModule {

    public static multiItem solidfuel;
    private boolean isShiny = false;
    private String suffix;

    public solidfuelModule(pluginBase parent) {
        super(parent);
    }

    public static void load(pluginBase parent) {
        baseModule b = new solidfuelModule(parent);
        b.register();
    }

    @Override
    protected void defaults() {
        this.addDefault("# These options are for the Solid Fuel Module");
        this.addDefault("solidfuelModule_Enabled=true");
        this.addDefault("solidfuel_ItemID=" + String.valueOf(core.ItemIDs[7]));
        this.addDefault("solidfuel_InternalName=solidfuel");
        this.addDefault("solidfuel_isShiny=true");
        this.addDefault("solidfuel_Suffix=Infused Bar");
        this.addDefault("SolidBiofuel_MJt=5");
        this.addDefault("SolidBiofuel_BurnTime=" + String.valueOf(((80000 + (80000 * (50 / 100))) / 2)));
    }

    @Override
    protected void init() {
        if (this.getOptionBool("solidfuelModule_Enabled")) {
            this.isShiny = this.getOptionBool("solidfuel_isShiny");
            this.suffix = " " + this.getOption("solidfuel_Suffix");
            solidfuel = new multiItem(this.getOptionInt("solidfuel_ItemID"), this.getOption("solidfuel_InternalName"));
            //----------------------------------------------
            solidfuel.metaMap.put("RESERVED DO NOT USE", 0);
            solidfuel.metaMap.put("Solid Biofuel", 1);
            //----------------------------------------------
            solidfuel.add("solidplaceholder",0, 23 + 2, "PLACEHOLDER");
            solidfuel.add("solidbiofuelbar", solidfuel.metaMap.get("Solid Biofuel"), 23 + 2, "Biofuel" + this.suffix, this.isShiny);
        }
        recipes();
    }

    @Override
    protected void recipes() {
        if (this.getOptionBool("solidfuelModule_Enabled")) {
            this.infusedBarRecipes();
            this.fuelRecipes();

        }
    }

    private void infusedBarRecipes() {
        ArrayList<barObject> bars = new ArrayList();
        bars.add(new barObject(new ItemStack(solidfuel, 1, solidfuel.metaMap.get("Solid Biofuel")), new LiquidStack(ItemInterface.getItem("liquidBiofuel").getItem(), 1000), ItemInterface.getItem("mulch")));
        for (barObject b : bars) {
            if (b != null) {
                addInfusedRecipe(b);
            }
        }

    }

    private void addInfusedRecipe(barObject b) {
        ItemStack baseBar;
        ArrayList<ItemStack> items = new ArrayList();
        items.add(ItemInterface.getItem("propolis"));
        items.add(ItemInterface.getItem("pollen"));
        ArrayList<Object[]> recipes = makeRecipes(b, items);
        if (this.getOptionBool("SugaryPeat_Enabled")) {
            baseBar = new ItemStack(peatModule.sugarypeat, 1, peatModule.sugarypeat.metaMap.get("Sugary Peat"));
        } else {
            baseBar = ItemInterface.getItem("peat");
        }
        for (Object[] o : recipes) {
            RecipeManagers.carpenterManager.addRecipe(10, b.getLiquidStack(), baseBar, b.getItemStack(), o);
        }
    }

    private void fuelRecipes() {
        ItemStack fuel = new ItemStack(solidfuel, 1, solidfuel.metaMap.get("Solid Biofuel"));
        int burntime = this.getOptionInt("SolidBiofuel_BurnTime");
        int mjt = this.getOptionInt("SolidBiofuel_MJt");
        FuelManager.copperEngineFuel.put(solidfuel.shiftedIndex, new EngineCopperFuel(fuel, mjt, burntime));
    }

    private ArrayList<Object[]> makeRecipes(barObject b, ArrayList<ItemStack> a) {
        ArrayList<Object[]> list = new ArrayList();
        for (ItemStack i : a) {
            list.add(new Object[]{"MMM",
                        "RPR",
                        "MMM",
                        Character.valueOf('M'), b.getCatalyst(),
                        Character.valueOf('P'), i,
                        Character.valueOf('R'), ItemInterface.getItem("royalJelly")
                    });
        }
        return list;
    }
}
