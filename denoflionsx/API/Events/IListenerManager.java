package denoflionsx.API.Events;

public interface IListenerManager {
    
    public void register(Object listener);
    
    public void notifyListeners(Object l);
    
}
