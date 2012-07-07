package net.minecraft.src.denoflionsx.plugins.Buildcraft.BC2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import net.minecraft.src.Item;

public class addLiquidBC2 {

    public static void add(int id, Item filled) {
        Class c;
        Field f;
        Class c2;
        Object o;
        Constructor make;

        // Do BC2 Stuff.
        try {
            c = Class.forName("buildcraft.api.API");
            f = c.getField("liquids");
            c2 = Class.forName("buildcraft.api.LiquidData");
            make = c2.getDeclaredConstructor(new Class[]{int.class, int.class});
            o = make.newInstance(id, filled.shiftedIndex);
            LinkedList fList = (LinkedList) f.get(null);
            LinkedList fListCopy = fList;
            fList.add(c2.cast(o));
            c.getField("liquids").set(fListCopy, fList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
