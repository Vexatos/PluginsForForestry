package net.minecraft.server.denoflionsx.plugins.Forestry;

import java.util.ArrayList;

public class StillHack
{
    public static void engage()
    {
        try
        {
            Class[] var0 = Class.forName("forestry.factory.MachineDistillation").getDeclaredClasses();
            int var1 = -1;

            for (int var2 = 0; var2 < var0.length; ++var2)
            {
                if (var0[var2].getName().equals("forestry.factory.MachineDistillation$RecipeManager"))
                {
                    var1 = var2;
                }
            }

            ArrayList var5 = (ArrayList)var0[var1].getField("recipes").get((Object)null);
            var5.remove(var5.size() - 1);
            var0[var1].getField("recipes").set(var5, var5);
        }
        catch (Exception var4)
        {
            ;
        }
    }
}
