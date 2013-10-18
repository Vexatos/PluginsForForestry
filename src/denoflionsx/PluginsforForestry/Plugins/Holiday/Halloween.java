package denoflionsx.PluginsforForestry.Plugins.Holiday;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.client.IconConstants;
import denoflionsx.denLib.NewConfig.ConfigField;
import java.util.Calendar;

public class Halloween implements IPfFPlugin {

    @ConfigField(category = "holiday")
    public static boolean forceHalloween = false;

    @Override
    public void onPreLoad() {
        Calendar current = Calendar.getInstance();
        if (current.get(Calendar.MONTH) == Calendar.OCTOBER || forceHalloween) {
            IconConstants.woodenBucket = "wooden_bucket_spooky";
            PfF.Proxy.print("Halloween mode activated!");
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
    }
}
