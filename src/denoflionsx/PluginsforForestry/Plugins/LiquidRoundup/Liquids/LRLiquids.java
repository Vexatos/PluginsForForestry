package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Liquids;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.denLib.Lib.denLib;
import net.minecraftforge.liquids.LiquidStack;

public class LRLiquids {

    public static BiMap<String, LiquidStack> LRLiquids = HashBiMap.create();
    
    public static final String liquidPeat_Dictionary = "peat";
    public static final String veggieJuice_Dictionary = "vegetable";

    public static String getLiquidNameFromLiquidStack(LiquidStack l) {
        for (LiquidStack test : LRLiquids.values()){
            if (test.isLiquidEqual(l)){
                return LRLiquids.inverse().get(test);
            }
        }
        return denLib.StringUtils.readError;
    }
}
