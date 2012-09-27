package denoflionsx.API.Events;

import java.util.EventObject;
import net.minecraft.src.ItemStack;

public class EventItemInitialized extends EventObject{
    
    private ItemStack item;
    private String name;

    public EventItemInitialized(ItemStack item, String tag, Object source) {
        super(source);
        this.item = item;
        this.name = tag;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }
}
