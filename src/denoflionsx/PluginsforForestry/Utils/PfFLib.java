package denoflionsx.PluginsforForestry.Utils;

import net.minecraftforge.liquids.LiquidDictionary;

public class PfFLib {

    public static class PffStringUtils {

        public static String cleanLiquidNameFromEvent(LiquidDictionary.LiquidRegisterEvent event) {
            return Character.toUpperCase(event.Name.charAt(0)) + event.Name.substring(1);
        }
    }
}
