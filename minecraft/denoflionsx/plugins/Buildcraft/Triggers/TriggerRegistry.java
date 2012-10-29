package denoflionsx.plugins.Buildcraft.Triggers;

import denoflionsx.plugins.BlueSilkWorm.Triggers.*;

public class TriggerRegistry {
    
    public static needsFood FoodTrigger;
    public static needsWorm WormTrigger;
    public static hasCocoon CocoonTrigger;
    public static hasMoth MothTrigger;
    public static needsBiomass BiomassTrigger;
    
    public static void init(){
        FoodTrigger = new needsFood();
        WormTrigger = new needsWorm();
        CocoonTrigger = new hasCocoon();
        MothTrigger = new hasMoth();
        BiomassTrigger = new needsBiomass();
    }
    
}
