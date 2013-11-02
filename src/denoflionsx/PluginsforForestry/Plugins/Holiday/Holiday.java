package denoflionsx.PluginsforForestry.Plugins.Holiday;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Plugins.Holiday.Objects.DateObject;
import denoflionsx.PluginsforForestry.Plugins.Holiday.Objects.HolidayObject;
import java.util.Calendar;

public class Holiday implements IPfFPlugin {

    private HolidayObject[] days = new HolidayObject[]{
        new HolidayObject("Halloween", new DateObject(Calendar.OCTOBER).setWholeMonth(true), "holiday/wooden_bucket_spooky", "item.pff.woodenbucket.halloween.name"),
        new HolidayObject("Thanksgiving (US)", new DateObject(Calendar.NOVEMBER, 21, 28), "holiday/pilgrim_bucket", "item.pff.woodenbucket.thanksgiving.name")
    };

    @Override
    public void onPreLoad() {
        for (HolidayObject o : days) {
            if (o.getRunningDate().isValidDate()) {
                o.setupHolidayContent();
            }
        }
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onPostLoad() {
    }
}
