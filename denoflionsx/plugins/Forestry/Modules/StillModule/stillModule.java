package denoflionsx.plugins.Forestry.Modules.StillModule;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.EnumForestryLiquids;
import forestry.api.recipes.RecipeManagers;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.src.Item;
import denoflionsx.Old.baseModule;
import denoflionsx.Old.pluginBase;

public class stillModule extends baseModule {
    
    private Item biomass = EnumForestryLiquids.BIOMASS.getLiquid().getItem();
    private Item biofuel = EnumForestryLiquids.BIOFUEL.getLiquid().getItem();
    
    public stillModule(pluginBase parent) {
        super(parent);
    }
    
    public static void load(pluginBase parent){
        baseModule b = new stillModule(parent);
        b.register();
    }

    @Override
    protected void defaults() {
        this.addDefault("# These options are for the Forestry Still");
        this.addDefault("# StillFix removes SirSengir's Still recipe that has a hardcoded 10 -> 3 ratio...");
        this.addDefault("# ...and substitutes my own with configurable ratio.");
        this.addDefault("# If StillFix is disabled the PerCast options are not used.");
        this.addDefault("StillFix=true");
        this.addDefault("Still_BiomassPerCast=10");
        this.addDefault("Still_BiofuelPerCast=5");
        this.addDefault("# This sets the still's speed. Mim value is 1.");
        this.addDefault("Still_WorkCycles=100");
    }

    @Override
    protected void init() {
        if (!this.getOptionBool("StillFix")){
            return;
        }
        String RecipeManager = "MachineStill$RecipeManager";
        String ClassPath = "forestry.factory.gadgets.";
        String fieldname = "recipes";
        try{
            Class still = Class.forName(ClassPath + RecipeManager);
            Field recipes = still.getField(fieldname);
            recipes.setAccessible(true);
            ArrayList Recipes = (ArrayList)recipes.get(null);
            Recipes.remove(0);
            recipes.set(null,Recipes);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        recipes();
    }

    @Override
    protected void recipes() {
        RecipeManagers.stillManager.addRecipe(this.getOptionInt("Still_WorkCycles"), new LiquidStack(biomass,this.getOptionInt("Still_BiomassPerCast")), new LiquidStack(biofuel,this.getOptionInt("Still_BiofuelPerCast")));
    }
}
