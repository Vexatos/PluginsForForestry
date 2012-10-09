package denoflionsx.plugins.Buildcraft.Triggers;

import denoflionsx.Machine.Triggers.TriggerHasWork;
import denoflionsx.plugins.BlueSilkWorm.Triggers.TriggerHasCocoon;
import denoflionsx.plugins.BlueSilkWorm.Triggers.TriggerHasMoth;

public class Triggers {
    
    public static TriggerHasCocoon hasCocoon;
    public static TriggerHasMoth hasMoth;
    public static TriggerHasWork hasWork;
    
    public static void init(){
        hasCocoon = new TriggerHasCocoon(900);
        hasMoth = new TriggerHasMoth(901);
        hasWork = new TriggerHasWork(902);
    }
    
}
