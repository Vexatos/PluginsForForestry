package denoflionsx.PluginsforForestry.Plugins.Holiday;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.client.IconConstants;
import denoflionsx.denLib.NewConfig.ConfigField;
import java.util.Calendar;

public class Thanksgiving_US implements IPfFPlugin {
    
    @ConfigField(category = "holiday")
    public static boolean forceThanksgiving_US = false;
    public static final DateObject date = new DateObject(Calendar.NOVEMBER, 21, 28);

    @Override
    public void onPreLoad() {
        if (date.isValidDate() || forceThanksgiving_US){
            IconConstants.woodenBucket = "holiday/pilgrim_bucket";
            PfFTranslator.instance.overrideKey("item.pff.woodenbucket.name", "item.pff.woodenbucket.thanksgiving.name");
            PfF.Proxy.print("Thanksgiving (US) mode activated!");
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
    }
}
