package denoflionsx.PluginsforForestry_PluginTweaks.Config;

import denoflionsx.PluginsforForestry.API.Annotations.Tunable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;

public class TweaksTuning {

    public static boolean tuning_enabled = false;

    static {
        CoreTuning.tuning.registerTunable(TweaksTuning.class);
    }

    public static class Enables {

        @Tunable(category = "tweaks.enables")
        public static boolean fencelight_enabled = false;
        @Tunable(category = "tweaks.enables")
        public static boolean torchlightincrease_enabled = false;
        @Tunable(category = "tweaks.enables")
        public static boolean fenceGateLight_enabled = false;
        @Tunable(category = "tweaks.enables")
        public static boolean smeltLeatherFromRottenFlesh = false;
    }

    public static class Tweaks {

        @Tunable(category = "tweaks.tweaks")
        public static int egg_stacksize = 16;
        @Tunable(category = "tweaks.tweaks")
        public static int potion_stacksize = 1;
        @Tunable(category = "tweaks.tweaks")
        public static int boat_stacksize = 1;
        @Tunable(category = "tweaks.tweaks")
        public static int enderpearl_stacksize = 16;
    }
}
