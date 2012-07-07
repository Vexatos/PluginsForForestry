package net.minecraft.server.denoflionsx.plugins.Buildcraft.BC2;

import net.minecraft.server.denoflionsx.core.core;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class RefineryManager
{
    LinkedList recipes;
    LinkedList recipesCopy;
    Field injectInto;
    Field hardcodedID;
    int ID;
    int IDCopy;

    public void hookRefinery()
    {
        try
        {
            Class var1 = Class.forName("buildcraft.factory.TileRefinery");
            Field var2 = var1.getField("recipes");
            LinkedList var3 = (LinkedList)var2.get((Object)null);
            this.recipes = var3;
            this.recipesCopy = this.recipes;
            this.injectInto = var2;
            Class var4;

            if (!core.isBukkit)
            {
                var4 = Class.forName("BuildCraftCore");
            }
            else
            {
                var4 = Class.forName("net.minecraft.server.BuildCraftCore");
            }

            Field var5 = var4.getField("refineryInput");
            this.hardcodedID = var5;
            this.IDCopy = this.hardcodedID.getInt((Object)null);
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }
    }

    public void addRecipe(Integer[] var1)
    {
        try
        {
            this.hookRefinery();
            Class var2 = Class.forName("buildcraft.factory.RefineryRecipe");
            Constructor var4 = var2.getDeclaredConstructor(new Class[] {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE});
            Object var3 = var4.newInstance(new Object[] {var1[0], var1[1], var1[2], var1[3], var1[4], var1[5], var1[6], var1[7]});
            this.recipes.add(var2.cast(var3));
            this.injectInto.set(this.recipesCopy, this.recipes);
            this.ID = var1[0].intValue();
            this.IDCopy = this.ID;
            this.hardcodedID.set(Integer.valueOf(this.IDCopy), Integer.valueOf(this.ID));
        }
        catch (Exception var5)
        {
            var5.printStackTrace();
        }
    }
}
