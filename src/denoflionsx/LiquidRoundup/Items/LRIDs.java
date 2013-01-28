package denoflionsx.LiquidRoundup.Items;

public class LRIDs {
    
    public static final int base = 5212;
    public static int current = base - 1;
    public static int LiquidIDs[] = new int[256];
    public static int ContainerIDs[] = new int[128];
    
    static{
        // Reserve these ids so my other mods don't try to use them.
        // These will be auto-used as the liquid system expands.
        for (int i = 0; i < 256; i++){
            LiquidIDs[i] = getNewID();
        }
        for (int i = 0; i < 128; i++){
            ContainerIDs[i] = getNewID();
        }
    }
    
    public static int getNewID(){
        current++;
        return current;
    }
    
}
