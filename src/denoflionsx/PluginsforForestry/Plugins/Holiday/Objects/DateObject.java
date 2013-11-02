package denoflionsx.PluginsforForestry.Plugins.Holiday.Objects;

import java.util.Calendar;

public class DateObject {

    private static final Calendar cal = Calendar.getInstance();
    private int month;
    private int startDay = -1;
    private int endDay = -1;
    private boolean wholeMonth = false;

    public DateObject(int month, int startDay, int endDay) {
        this.month = month;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public DateObject(int month) {
        this.month = month;
    }

    public boolean isValidDate() {
        if (cal.get(Calendar.MONTH) == month) {
            if (wholeMonth) {
                return wholeMonth;
            }
            if (cal.get(Calendar.DAY_OF_MONTH) >= startDay && cal.get(Calendar.DAY_OF_MONTH) <= endDay) {
                return true;
            }
        }
        return false;
    }

    public DateObject setWholeMonth(boolean wholeMonth) {
        this.wholeMonth = wholeMonth;
        return this;
    }
}
