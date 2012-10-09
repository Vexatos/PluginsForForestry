package denoflionsx.plugins.BlueSilkWorm.Managers;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeState;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGenericManager;
import denoflionsx.plugins.BlueSilkWorm.LifeStates.SilkWormGenericLifeState;
import java.util.HashMap;

public class SilkWormLifeStateManager implements ISilkWormGenericManager {

    public static HashMap<String, ISilkWormLifeState> lifestates = new HashMap();

    public SilkWormLifeStateManager() {
        register(new SilkWormGenericLifeState("Normal", true));
        register(new SilkWormGenericLifeState("Undead", false));
    }

    @Override
    public final void register(Object state) {
        if (state instanceof ISilkWormLifeState) {
            ISilkWormLifeState s = (ISilkWormLifeState) state;
            lifestates.put(s.getLifeStateName(), s);
        }
    }

    @Override
    public ISilkWormLifeState getObject(String name) {
        if (lifestates.get(name) != null) {
            return lifestates.get(name);
        } else {
            return null;
        }
    }

    @Override
    public int getNumberOfRegisteredObjects() {
        return lifestates.size();
    }

    @Override
    public HashMap getHashMap() {
        return lifestates;
    }
}
