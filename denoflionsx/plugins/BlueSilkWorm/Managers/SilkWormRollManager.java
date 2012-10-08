package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.denLib.denLib;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGender;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpanCategory;
import denoflionsx.plugins.BlueSilkWorm.LifeSpans.SilkWormLifeSpan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SilkWormRollManager {

    private Random rng = new Random();

    public ISilkWormGender getNewGender() {
        int roll = rng.nextInt(SilkWormManagers.Genders.getNumberOfRegisteredObjects());
        return (ISilkWormGender) denLib.dumpMapValues(SilkWormManagers.Genders.getHashMap()).get(roll);
    }

    public ISilkWormLifeSpan getNewLifeSpanNoGenetics() {
        int worm = rng.nextInt(getAbsoluteMaxLifeSpan());
        int cocoon = rng.nextInt(getAbsoluteMaxLifeSpan());
        return new SilkWormLifeSpan(worm,cocoon);
    }

    public ISilkWormLifeSpanCategory whereDoesThisFall(int roll) {
        ArrayList<Object> times = denLib.dumpMapValues(SilkWormManagers.LifeSpans.getHashMap());
        for (Object o : times) {
            if (o instanceof ISilkWormLifeSpanCategory) {
                ISilkWormLifeSpanCategory cat = (ISilkWormLifeSpanCategory) o;
                if (cat.isInRange(roll)){
                    return cat;
                }
            }
        }
        return null;
    }

    public int getAbsoluteMaxLifeSpan() {
        ArrayList<Object> times = denLib.dumpMapValues(SilkWormManagers.LifeSpans.getHashMap());
        ArrayList<Integer> maxes = new ArrayList();
        for (Object o : times) {
            if (o instanceof ISilkWormLifeSpanCategory) {
                ISilkWormLifeSpanCategory cat = (ISilkWormLifeSpanCategory) o;
                maxes.add(cat.getCategoryMax());
            }
        }
        Collections.sort(maxes);
        int max = maxes.get(maxes.size() - 1);
        return max;
    }
}
