package forestry.api.apiculture;

import forestry.api.core.INBTTagable;
import forestry.api.genetics.IEffectData;

public interface IBeekeepingLogic extends INBTTagable
{
    int getBreedingTime();

    int getTotalBreedingTime();

    IBee getQueen();

    IEffectData[] getEffectData();

    void update();
}
