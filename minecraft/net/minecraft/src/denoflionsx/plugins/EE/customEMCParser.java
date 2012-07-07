package net.minecraft.src.denoflionsx.plugins.EE;

import java.util.HashMap;

public class customEMCParser {
    
    public static HashMap<Integer, HashMap> Values = new HashMap();
    
    public static void parse(String line){
        String[] split = line.split(",");
        int id = Integer.valueOf(split[0]);
        int dmg = Integer.valueOf(split[1]);
        int value = Integer.valueOf(split[2]);
        HashMap<Integer, Integer> temp = new HashMap();
        if (Values.get(id) != null){
            temp = Values.get(id);
        }
        temp.put(dmg, value);
        Values.put(id, temp);
    } 
}
