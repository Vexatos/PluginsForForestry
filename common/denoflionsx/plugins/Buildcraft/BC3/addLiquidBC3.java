package denoflionsx.plugins.Buildcraft.BC3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.src.ItemStack;

/**
 *
 * @author den
 */
public class addLiquidBC3 {

    public static void add(int id, ItemStack filled) {
        Class c;
        Field f;
        Class c2;
        Object o;
        Constructor make;
        try {
            c = Class.forName("buildcraft.api.BuildCraftAPI");
            f = c.getField("liquids");
            c2 = Class.forName("buildcraft.api.LiquidData");
            make = c2.getDeclaredConstructor(new Class[]{int.class, int.class, ItemStack.class});
            o = make.newInstance(id, id, filled);
            LinkedList fList = (LinkedList) f.get(null);
            fList.add(c2.cast(o));
            c.getField("liquids").set(null, fList);
        } catch (Exception ex) {
        }
    }
    
        public static void add(int id, ItemStack filled, ItemStack con) {
        Class c;
        Field f;
        Class c2;
        Object o;
        Constructor make;
        try {
            c = Class.forName("buildcraft.api.BuildCraftAPI");
            f = c.getField("liquids");
            c2 = Class.forName("buildcraft.api.LiquidData");
            make = c2.getDeclaredConstructor(new Class[]{int.class, int.class, ItemStack.class, ItemStack.class});
            o = make.newInstance(id, id, filled, con);
            LinkedList fList = (LinkedList) f.get(null);
            fList.add(c2.cast(o));
            c.getField("liquids").set(null, fList);
        } catch (Exception ex) {
        }
    }
}
