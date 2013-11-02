package denoflionsx.PluginsforForestry.Plugins.Holiday.Objects;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.client.IconConstants;

public class HolidayObject {

    private String name;
    private DateObject runningDate;
    private String icon;
    private String key;

    public HolidayObject(String name, DateObject runningDate, String icon, String key) {
        this.name = name;
        this.runningDate = runningDate;
        this.icon = icon;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public DateObject getRunningDate() {
        return runningDate;
    }
    
    public void setupHolidayContent(){
        IconConstants.woodenBucket = icon;
        PfFTranslator.instance.overrideKey("item.pff.woodenbucket.name", key);
        PfF.Proxy.print(name.concat(" mode activated!"));
    }
}
