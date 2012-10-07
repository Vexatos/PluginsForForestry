package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.denLib.denLib;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGender;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeSpan;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormRollManager;
import java.util.Random;

public class SilkWormRollManager implements ISilkWormRollManager{

    @Override
    public ISilkWormLifeSpan getNewLife() {
        Random rng = new Random();
        int roll = rng.nextInt(SilkWormManagers.LifeSpans.getNumberOfRegisteredObjects());
        return (ISilkWormLifeSpan) denLib.dumpMapValues(SilkWormManagers.LifeSpans.getHashMap()).get(roll);
    }

    @Override
    public ISilkWormGender getNewGender() {
        Random rng = new Random();
        int roll = rng.nextInt(SilkWormManagers.Genders.getNumberOfRegisteredObjects());
        return (ISilkWormGender) denLib.dumpMapValues(SilkWormManagers.Genders.getHashMap()).get(roll);
    }

}
