package denoflionsx.Machine.Client;

public class IconIndexLogic {
    
    public static int[] getCoorsFromIndex(int index){
        int x;
        int y;
        int s = index;
        while (s >= 16){
            s-=16;
        }
        x = s * 16;
        y = (index / 16) * 16;
        return new int[]{x,y};
    }
}
