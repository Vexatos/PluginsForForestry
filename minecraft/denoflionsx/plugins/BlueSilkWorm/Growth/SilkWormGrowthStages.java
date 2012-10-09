package denoflionsx.plugins.BlueSilkWorm.Growth;

import denoflionsx.Enums.Colors;
import denoflionsx.plugins.BlueSilkWorm.Interfaces.ISilkWormGrowthState;

public class SilkWormGrowthStages {
    
    public static final ISilkWormGrowthState WORM = new SilkWormGenericGrowthState(0,Colors.shiftRow(3, 7));
    public static final ISilkWormGrowthState COCOON = new SilkWormGenericGrowthState(1,Colors.shiftRow(4, 7));
    public static final ISilkWormGrowthState MOTH = new SilkWormGenericGrowthState(2,Colors.shiftRow(8, 7));
    public static final ISilkWormGrowthState DEAD_WORM = new SilkWormGenericGrowthState(3,Colors.shiftRow(6, 7));
    public static final ISilkWormGrowthState ZOMBIE_WORM = new SilkWormGenericGrowthState(4,Colors.shiftRow(7, 7));
 
}
