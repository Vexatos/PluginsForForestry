package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;

@InternalUseOnly
public class PfFSlotObject {
    
    private int slotnum;
    private int x;
    private int y;

    public PfFSlotObject(int slotnum, int x, int y) {
        this.slotnum = slotnum;
        this.x = x;
        this.y = y;
    }

    public int getSlotnum() {
        return slotnum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
