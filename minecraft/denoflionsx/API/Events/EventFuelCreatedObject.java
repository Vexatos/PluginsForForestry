package denoflionsx.API.Events;

import net.minecraft.src.ItemStack;

public class EventFuelCreatedObject {
    
    private ItemStack liquid;
    private int MJt;
    private int BurnTime;

    public EventFuelCreatedObject(ItemStack liquid, int MJt, int BurnTime) {
        this.liquid = liquid;
        this.MJt = MJt;
        this.BurnTime = BurnTime;
    }

    public int getBurnTime() {
        return BurnTime;
    }

    public int getMJt() {
        return MJt;
    }

    public ItemStack getLiquid() {
        return liquid;
    }
}
