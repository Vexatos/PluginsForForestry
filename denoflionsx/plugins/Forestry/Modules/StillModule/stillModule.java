package denoflionsx.plugins.Forestry.Modules.StillModule;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.Enums.EnumForestryLiquids;
import forestry.api.recipes.RecipeManagers;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.src.Item;
import denoflionsx.Old.baseModule;
import denoflionsx.Old.pluginBase;
import denoflionsx.core.IPfFModuleTemplate;

public class stillModule extends IPfFModuleTemplate {

    private Item biomass = EnumForestryLiquids.BIOMASS.getLiquid().getItem();
    private Item biofuel = EnumForestryLiquids.BIOFUEL.getLiquid().getItem();

    public stillModule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("# These options are for the Forestry Still");
        this.config.addDefault("# StillFix removes SirSengir's Still recipe that has a hardcoded 10 -> 3 ratio...");
        this.config.addDefault("# ...and substitutes my own with configurable ratio.");
        this.config.addDefault("# If StillFix is disabled the PerCast options are not used.");
        this.config.addDefault("Still_BiomassPerCast=10");
        this.config.addDefault("Still_BiofuelPerCast=5");
        this.config.addDefault("# This sets the still's speed. Mim value is 1.");
        this.config.addDefault("Still_WorkCycles=100");
    }

    @Override
    public void doSetup() {
        String RecipeManager = "MachineStill$RecipeManager";
        String ClassPath = "forestry.factory.gadgets.";
        String fieldname = "recipes";
        try {
            Class still = Class.forName(ClassPath + RecipeManager);
            Field recipes = still.getField(fieldname);
            recipes.setAccessible(true);
            ArrayList Recipes = (ArrayList) recipes.get(null);
            Recipes.remove(0);
            recipes.set(null, Recipes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void recipes() {
        RecipeManagers.stillManager.addRecipe(this.config.getOptionInt("Still_WorkCycles"), new LiquidStack(biomass, this.config.getOptionInt("Still_BiomassPerCast")), new LiquidStack(biofuel, this.config.getOptionInt("Still_BiofuelPerCast")));
    }
}
