package denoflionsx.plugins.BlueSilkWorm.LifeSpans;

import denoflionsx.plugins.BlueSilkWorm.Helpers.SilkWormHelper;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;

public class SilkWormGenericLifeSpan implements ISilkWormLifeSpan{
    
    private String name;
    private int length;

    public SilkWormGenericLifeSpan(String name, int length) {
        this.name = name;
        this.length = SilkWormHelper.ConvertMinutesToTicks(length);
    }

    @Override
    public int getLifeLength() {
        return this.length;
    }

    @Override
    public String getLifeSpanName() {
        return this.name;
    } 
    
}
