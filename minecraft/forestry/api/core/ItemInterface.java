package forestry.api.core;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemInterface
{
    public static ItemStack getItem(String var0)
    {
        ItemStack var1 = null;

        try
        {
            String var2 = ItemInterface.class.getPackage().getName();
            var2 = var2.substring(0, var2.lastIndexOf(46));
            String var3 = var2.substring(0, var2.lastIndexOf(46)) + ".core.config.ForestryItem";
            Object var4 = Class.forName(var3).getField(var0).get((Object)null);

            if (var4 instanceof Item)
            {
                var1 = new ItemStack((Item)var4);
            }
            else if (var4 instanceof ItemStack)
            {
                var1 = (ItemStack)var4;
            }
        }
        catch (Exception var5)
        {
            FMLLog.warning("Could not retrieve Forestry item identified by: " + var0, new Object[0]);
        }

        return var1;
    }
}
