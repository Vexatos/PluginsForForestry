package denoflionsx.plugins.BlueSilkWorm.LifeSpans;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;

public class SilkWormLifeSpan implements ISilkWormLifeSpan{
    
    private int worm;
    private int cocoon;

    public SilkWormLifeSpan(int worm, int cocoon) {
        this.worm = worm;
        this.cocoon = cocoon;
    }

    @Override
    public int getActualCocoonLifespan() {
        return cocoon;
    }

    @Override
    public int getActualWormLifespan() {
        return worm;
    }
}
