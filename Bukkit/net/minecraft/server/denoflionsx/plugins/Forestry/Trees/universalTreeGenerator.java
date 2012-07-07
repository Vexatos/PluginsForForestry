package net.minecraft.server.denoflionsx.plugins.Forestry.Trees;

import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.World;

public class universalTreeGenerator
{
    public static HashMap Trees = new HashMap();

    public void growTree(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getData(var2, var3, var4);

        for (int var6 = 0; var6 < 5; ++var6)
        {
            var1.setTypeIdAndData(var2, var3 + var6, var4, ((universalTreeData)Trees.get(Integer.valueOf(var5))).getLog().id, ((universalTreeData)Trees.get(Integer.valueOf(var5))).getLogMeta());
        }

        var1.setTypeIdAndData(var2, var3 + 5, var4, ((universalTreeData)Trees.get(Integer.valueOf(var5))).getLeaf().id, ((universalTreeData)Trees.get(Integer.valueOf(var5))).getLeafMeta());
    }

    public static void add(int var0, Block var1, Block var2, Block var3, int var4, int var5, int var6)
    {
        Trees.put(Integer.valueOf(var0), new universalTreeData(var1, var2, var3, var4, var5, var6));
    }

    public static void add(int var0, Block var1, Block var2, Block var3)
    {
        Trees.put(Integer.valueOf(var0), new universalTreeData(var1, var2, var3));
    }
}
