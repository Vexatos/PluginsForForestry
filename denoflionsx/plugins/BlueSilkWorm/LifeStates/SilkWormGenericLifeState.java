package denoflionsx.plugins.BlueSilkWorm.LifeStates;

import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormLifeState;

public class SilkWormGenericLifeState implements ISilkWormLifeState{

    private String name;
    private boolean canBonk;

    public SilkWormGenericLifeState(String name, boolean canBonk) {
        this.name = name;
        this.canBonk = canBonk;
    }

    @Override
    public boolean canLifeStateReproduce() {
        return this.canBonk;
    }

    @Override
    public String getLifeStateName() {
        return this.name;
    }
 
}
