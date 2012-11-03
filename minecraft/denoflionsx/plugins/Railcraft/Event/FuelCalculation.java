package denoflionsx.plugins.Railcraft.Event;

public class FuelCalculation {
    
    public static int getFuelValue(int MJt, int BurnTime){
        float multi = 1.25f;
        int div = 5;
        int value = Math.round(((BurnTime * multi) * MJt) / div);
        return value;
    }
}
