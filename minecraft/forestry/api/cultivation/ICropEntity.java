package forestry.api.cultivation;

import java.util.ArrayList;

public interface ICropEntity
{
    boolean isHarvestable();

    int[] getNextPosition();

    ArrayList doHarvest();
}
