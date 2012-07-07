package net.minecraft.server.denoflionsx.plugins.BetterFarming;

import net.minecraft.server.denoflionsx.core.core;
import java.lang.reflect.Method;
import net.minecraft.server.Block;
import net.minecraft.server.World;

public class growHook
{
    public static Class BlockCustomTree;
    protected static Class[] parameters = new Class[] {World.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
    protected static Method grow;
    protected static String treeclass = "BlockCustomTree";

    public static void growTree(World var0, int var1, int var2, int var3, Block var4)
    {
        int var5 = var0.getTypeId(var1, var2, var3);

        if (var5 == var4.id)
        {
            var0.setTypeIdAndData(var1, var2 - 1, var3, Block.DIRT.id, 0);

            try
            {
                grow.invoke(BlockCustomTree.cast(var4), new Object[] {var0, Integer.valueOf(var1), Integer.valueOf(var2), Integer.valueOf(var3)});
            }
            catch (Exception var7)
            {
                var7.printStackTrace();
            }
        }

        var0.setTypeIdAndData(var1, var2 - 1, var3, Block.SAND.id, 0);
    }

    public static void engage()
    {
        try
        {
            if (core.isBukkit)
            {
                treeclass = "net.minecraft.server." + treeclass;
            }

            BlockCustomTree = Class.forName(treeclass);
            grow = BlockCustomTree.getMethod("growTree", parameters);
        }
        catch (Exception var1)
        {
            var1.printStackTrace();
        }
    }
}
