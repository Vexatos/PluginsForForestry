package net.minecraft.src.denoflionsx.API;

import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PFFItems {

    public static HashMap<String, ItemStack> registeredItems = new HashMap();

    // This one is for internal use only. See API class for access method.
    public static void registerItem(String s, Item i, int d) {

        ItemStack I = new ItemStack(i, 1, d);
        registeredItems.put(s, I);
        if (API.verbose) {
            System.out.println("Registered PFF Item " + s + " with API.");
        }
    }

    public static void registerItem(String s, Item i) {
        ItemStack I = new ItemStack(i, 1);
        registeredItems.put(s, I);
        if (API.verbose) {
            System.out.println("Registered PFF Item " + s + " with API.");
        }
    }
}
