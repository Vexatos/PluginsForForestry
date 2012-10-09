package denoflionsx.Events;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Events.*;
import denoflionsx.Annotations.PfFAnnotationSearch;
import denoflionsx.denLib.denLib;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public class PfFItemInitializedManager implements IListenerManager {

    public static ArrayList<Object> listeners = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        ItemStack z = (ItemStack) l;
        for (Object a : listeners) {
            if (a instanceof IItemListener) {
                IItemListener q = (IItemListener) a;
                q.itemInitialized(new EventItemInitialized(z,denLib.removeDotItemFromName(z.getItemName()),this));
            }
            PfFAnnotationSearch.MethodAnnotation m = PfFAnnotationSearch.AnnotatedMethod(a.getClass(), PfFEventTypes.ITEM_CREATED);
            if (m != null) {
                try {
                    m.getMethod().invoke(a, new EventItemInitialized(z,denLib.removeDotItemFromName(z.getItemName()),this));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void register(Object listener) {
        listeners.add(listener);
    }
}
