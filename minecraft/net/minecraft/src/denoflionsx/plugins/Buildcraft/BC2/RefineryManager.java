package net.minecraft.src.denoflionsx.plugins.Buildcraft.BC2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.src.denoflionsx.core.core;

public class RefineryManager {

    // The intent of this class is to reflect into BC and get control of the
    // Refinery recipe list and associated methods.
    public RefineryManager() {
    }
    LinkedList recipes;
    LinkedList recipesCopy;
    Field injectInto;
    Field hardcodedID;
    int ID;
    int IDCopy;

    public void hookRefinery() {
        try {
            Class TileRefinery = Class.forName("buildcraft.factory.TileRefinery");
            Field recipe = TileRefinery.getField("recipes");
            LinkedList recipesList = (LinkedList) recipe.get(null);
            this.recipes = recipesList;
            this.recipesCopy = this.recipes;
            this.injectInto = recipe;
            Class BuildcraftCore;
            if (!core.isBukkit) {
                BuildcraftCore = Class.forName("BuildCraftCore");
            } else {
                BuildcraftCore = Class.forName("net.minecraft.server.BuildCraftCore");
            }
            Field refineryInput = BuildcraftCore.getField("refineryInput");
            this.hardcodedID = refineryInput;
            this.IDCopy = (int) this.hardcodedID.getInt(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addRecipe(Integer[] params) {
        try {
            hookRefinery();
            Class RefineryRecipe = Class.forName("buildcraft.factory.RefineryRecipe");
            Object inject;
            Constructor newRecipe = RefineryRecipe.getDeclaredConstructor(new Class[]{int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class});
            inject = newRecipe.newInstance(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7]);
            recipes.add(RefineryRecipe.cast(inject));
            this.injectInto.set(this.recipesCopy, this.recipes);
            this.ID = params[0];
            this.IDCopy = this.ID;
            this.hardcodedID.set(this.IDCopy, this.ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
