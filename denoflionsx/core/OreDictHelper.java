package denoflionsx.core;

import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {
    
    public static boolean doesOreExist(String name){
        return !OreDictionary.getOres(name).isEmpty();
    }
}
