package denoflionsx.plugins.BlueSilkWorm.LifeSpans;

import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpanCategory;

public class SilkWormLifeSpanCategory implements ISilkWormLifeSpanCategory{

    private String label;
    private int[] minMax = new int[2];

    public SilkWormLifeSpanCategory(String label, int min, int max) {
        this.label = label;
        this.minMax[0] = SilkWormHelper.ConvertSecondsToTicks(min);
        this.minMax[1] = SilkWormHelper.ConvertSecondsToTicks(max);
    }

    @Override
    public String getCategoryLabel() {
        return label;
    }

    @Override
    public int getCategoryMax() {
        return minMax[1];
    }

    @Override
    public int getCategoryMin() {
        return minMax[0];
    }

    @Override
    public boolean isInRange(int test) {
        if (test >= this.getCategoryMin() && test <= this.getCategoryMax()){
            return true;
        }else{
            return false;
        }
    }
 
}
