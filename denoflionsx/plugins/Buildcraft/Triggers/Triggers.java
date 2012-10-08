package denoflionsx.plugins.Buildcraft.Triggers;

public class Triggers {
    
    public static TriggerHasCocoon hasCocoon;
    
    public static void init(){
        hasCocoon = new TriggerHasCocoon(900);
    }
    
}
