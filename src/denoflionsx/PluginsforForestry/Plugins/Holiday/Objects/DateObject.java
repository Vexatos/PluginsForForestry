package denoflionsx.PluginsforForestry.Plugins.Holiday.Objects;

import denoflionsx.PluginsforForestry.Core.PfF;
import java.util.Calendar;

public class DateObject {
    
    private static final Calendar cal = Calendar.getInstance();
    private int month;
    private int startDay = -1;
    private int endDay = -1;
    private boolean wholeMonth = false;
    
    static {
        PfF.Proxy.print("Current Date: " + (DateObject.getCal().get(Calendar.MONTH) + 1) + ", " + DateObject.getCal().get(Calendar.DAY_OF_MONTH) + ", " + DateObject.getCal().get(Calendar.YEAR));
    }
    
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
    
    public static Calendar getCal() {
        return cal;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getStartDay() {
        return startDay;
    }
    
    public int getEndDay() {
        return endDay;
    }
    
    public boolean isWholeMonth() {
        return wholeMonth;
    }
    
    @Override
    public String toString() {
        String s;
        if (this.startDay == -1) {
            s = "";
        } else {
            s = "Start: " + String.valueOf(this.startDay);
        }
        String e;
        if (this.endDay == -1) {
            e = "";
        } else {
            e = ", End: " + String.valueOf(this.endDay);
        }
        return String.valueOf(this.month + 1) + ", " + s.concat(e) + ". Whole month? " + String.valueOf(this.wholeMonth);
    }
}
