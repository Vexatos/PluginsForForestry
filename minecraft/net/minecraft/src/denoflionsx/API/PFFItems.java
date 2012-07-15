package net.minecraft.src.denoflionsx.API;

import java.util.HashMap;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class PFFItems {
    
    private static HashMap<String, ItemStack> registeredItems = new HashMap();
    
    public static ItemStack getItem(String s){
        
        ItemStack I = registeredItems.get(s);
        
        if (I != null){
            return I;
        }else{
            System.out.println("Unable to retrieve item: " + s + " from Plugins for Forestry!");
            return null;
        }
        
    }
    
    public static void registerItem(String s, Item i, int d){
        
        ItemStack I = new ItemStack(i,1,d);
        registeredItems.put(s,I);
        System.out.println("Registered PFF Item " + s + " with API.");
        
    }
    
}
