package net.minecraft.src.denoflionsx.denLib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class denLib {

    public static boolean detect(String mod) {
        return ModLoader.isModLoaded(mod);
    }

    public static Item getItem(String mod, String name) {
        boolean hooked = false;
        Item temp = null;

        try {
            temp = (Item) Class.forName(mod).getField(name).get(null);
            hooked = true;
        } catch (Exception ex) {
            print("" + ex);
            hooked = false;
        } finally {
            if (hooked) {
                //print("Item " + temp.getItemName() + " hooked!");
            }
        }

        return temp;
    }

    public static ItemStack getItemStack(String mod, String name) {
        boolean hooked = false;
        ItemStack temp = null;


        try {
            temp = (ItemStack) Class.forName(mod).getField(name).get(null);
            hooked = true;
        } catch (Exception ex) {
            print("" + ex);
            hooked = false;
        } finally {
            if (hooked) {
                //print("Item " + temp.getItemName() + " hooked!");
            }

            return temp;
        }
    }

    public static Block getBlock(String mod, String name) {
        boolean hooked = false;
        Block temp = null;

        try {
            temp = (Block) Class.forName(mod).getField(name).get(null);
            hooked = true;
        } catch (Exception ex) {
            print("" + ex);
            hooked = false;
        } finally {
            if (hooked) {
                //print("Block " + temp.getBlockName() + " hooked!");
            }
        }

        return temp;
    }

    public static void print(String msg) {

        System.out.println(msg);

    }

    public static boolean convertToBoolean(String bool) {
        bool = bool.toLowerCase();
        if (bool.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String toLowerCaseNoSpaces(String s){
        return s.toLowerCase().replaceAll("\\s", "");
    }

    public static void classSnoop(String param1) {
        try {
            Class c = Class.forName(param1);
            print("--------------------");
            print("Class Snoop Engaged");
            print("Class: " + param1);
            print("--------------------");
            Method[] m = c.getDeclaredMethods();
            Field[] f =c.getDeclaredFields();
            print("Defined Methods");
            print("--------------------");
            for (int i = 0; i < m.length; i++){
                print(m[i].getName());
            }
            print("--------------------");
            print("Defined Fields");
            print("--------------------");
            for (int i = 0; i < f.length; i++){
                print(f[i].getName());
            }
            print("--------------------");
            print("End of Snoop");
            print("--------------------");
        } catch (Exception ex) {
        }

    }
}
