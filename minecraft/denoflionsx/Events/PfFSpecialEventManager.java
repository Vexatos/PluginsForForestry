package denoflionsx.Events;

import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Events.EventSpecial;
import denoflionsx.API.Events.IListenerManager;
import denoflionsx.API.Events.ISpecialListener;
import denoflionsx.Annotations.PfFAnnotationSearch;
import denoflionsx.Annotations.PfFAnnotationSearch.MethodAnnotation;
import java.util.ArrayList;

public class PfFSpecialEventManager implements IListenerManager{
    
    public static ArrayList<Object> listeners = new ArrayList();
    // I really should do something with any exceptions that get swallowed.
    // I'll just store them for now.
    private static ArrayList<Exception> exceptionSwallow = new ArrayList();

    @Override
    public void notifyListeners(Object l) {
        String message = l.toString();
        for (Object a : listeners){
            if (a instanceof ISpecialListener){
                ISpecialListener q = (ISpecialListener)a;
                q.onSpecialEvent(new EventSpecial(this,message));
            }
            MethodAnnotation m = PfFAnnotationSearch.AnnotatedMethod(a.getClass(), PfFEventTypes.SPECIAL);
            if (m != null){
                try{
                    m.getMethod().invoke(a, new EventSpecial(this,message));
                }catch(Exception ex){
                    exceptionSwallow.add(ex);
                }
            }
        }
    }

    @Override
    public void register(Object listener) {
        listeners.add(listener);
    } 
    
}
