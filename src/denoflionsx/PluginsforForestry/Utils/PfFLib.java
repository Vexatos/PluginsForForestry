package denoflionsx.PluginsforForestry.Utils;

import com.google.common.collect.BiMap;
import denoflionsx.denLib.Lib.denLib;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import net.minecraftforge.liquids.LiquidDictionary;

public class PfFLib {

    public static class PffStringUtils {

        public static String cleanLiquidNameFromEvent(LiquidDictionary.LiquidRegisterEvent event) {
            return Character.toUpperCase(event.Name.charAt(0)) + event.Name.substring(1);
        }

        public static String getTextureFromName(String liquid) {
            return "item.pff." + denLib.StringUtils.removeSpaces(liquid.toLowerCase()) + ".still";
        }

        public static String Hash(String s) {
            try {
                return (new HexBinaryAdapter()).marshal(MessageDigest.getInstance("MD5").digest(s.getBytes()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return denLib.StringUtils.readError;
        }
    }

    public static class MathUtils {

        public static int getLastID(BiMap<Integer, String> map) {
            int ids[] = new int[map.size()];
            int temp = 0;
            for (Object o : map.keySet()) {
                Integer i = (Integer) o;
                ids[temp] = i;
                temp++;
            }
            Arrays.sort(ids);
            int f = 0;
            if (ids.length != 0) {
                f = ids[ids.length - 1];
            }
            f++;
            return f;
        }
    }
}
