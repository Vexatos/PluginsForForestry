package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGenericManager;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormRegistry;

public class SilkWormManagers {
    
    // This system may seem excessive, but it should be super easy to extend later on if
    // all of a sudden an awesome idea drops in.
    
    public static ISilkWormRegistry Registry = new SilkWormRegistry();
    public static ISilkWormGenericManager LifeStates = new SilkWormLifeStateManager();
    public static ISilkWormGenericManager Genders = new SilkWormGenderManager();
    public static SilkWormRollManager LifeManager = new SilkWormRollManager();
    public static ISilkWormGenericManager LifeSpans = new SilkWormLifeSpanManager();
}
