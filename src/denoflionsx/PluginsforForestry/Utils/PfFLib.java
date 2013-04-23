package denoflionsx.PluginsforForestry.Utils;

import denoflionsx.denLib.Lib.denLib;
import net.minecraftforge.liquids.LiquidDictionary;

public class PfFLib {

    public static class PffStringUtils {

        public static String cleanLiquidNameFromEvent(LiquidDictionary.LiquidRegisterEvent event) {
            return Character.toUpperCase(event.Name.charAt(0)) + event.Name.substring(1);
        }
        
        public static String getTextureFromName(String liquid){
            return "item.pff." + denLib.StringUtils.removeSpaces(liquid.toLowerCase()) + ".still";
        }
    }
}
