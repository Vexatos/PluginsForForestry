package net.minecraft.src.denoflionsx.core;

import java.util.HashMap;

public class PfFFuelManager {
    
    public static HashMap<Integer, Integer> fuelValues = new HashMap();
    
    public static void addFuel(int id, int value){
        fuelValues.put(id,value);
    }
    
    public static int getValue(int id){
        if (fuelValues.get(id) != null){
            return fuelValues.get(id);
        }else{
            return 0;
        }
    }
    
}
