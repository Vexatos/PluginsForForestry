package denoflionsx.Events;

import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.Events.IItemListener;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.denLib.denLib;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class PfFItemInitializedManager implements IListenerManager {

    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        ItemStack item = (ItemStack) l;
        for (Object o : listeners) {
            IItemListener z = (IItemListener) o;
            z.itemInitialized(new EventItemInitialized(item, denLib.removeDotItemFromName(item.getItemName()), this));
        }
    }

    @Override
    public void register(Object listener) {
        listeners.add(listener);
    }
}
