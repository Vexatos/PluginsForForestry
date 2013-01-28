package denoflionsx.PluginsforForestry_PluginForestry.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.PfF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.minecraftforge.common.ConfigCategory;

public class ForestryTuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(ForestryTuning.class);
    }

    public static class Items{
        
        @Tunable(category = "forestry.items")
        public static int BackpackT1_ItemID = 7357;
        @Tunable(category = "forestry.items")
        public static int BackpackT2_ItemID = 7358;
        
    }
    
    public static class Enables {

        @Tunable(category = "forestry.enables")
        public static boolean Biofuel_InBiogas = true;
        @Tunable(category = "forestry.enables")
        public static boolean LiquidPeat_Enabled = true;
        @Tunable(category = "forestry.enables")
        public static boolean Backpacks_Enabled = true;
    }

    public static class Fuels {

        static {
            ArrayList<String> removes = new ArrayList();
            ConfigCategory c = PfF.Core.config.getCategory("forestry.fuels");
            Iterator i = c.getValues().entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry pairs = (Map.Entry) i.next();
                String key = String.valueOf(pairs.getKey());
                if (key.equals("LiquidPeat_Coolant")) {
                    removes.add(key);
                    PfF.Proxy.print("Pruning removed config option from file: " + key);
                }
            }
            for (String s : removes) {
                c.getValues().remove(s);
            }
        }
        
        @Tunable(category = "forestry.fuels")
        public static int Biofuel_MJt = 5;
        @Tunable(category = "forestry.fuels")
        public static int Biofuel_BurnTime = 40000 - (int) (40000 * 0.2);
        @Tunable(category = "forestry.fuels")
        public static int LiquidPeat_MJt = 1;
        @Tunable(category = "forestry.fuels")
        public static int LiquidPeat_BurnTime = (5000 * 4);
    }
}
