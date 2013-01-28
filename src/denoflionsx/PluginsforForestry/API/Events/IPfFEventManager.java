package denoflionsx.PluginsforForestry.API.Events;

public interface IPfFEventManager {
    
    // Returns an int. Keep this int if you want to unregister later.
    public int registerListener(Object instance);
    
    // Return value is if the listener was sucesfully removed.
    public boolean unregisterListener(int index);
    
    public void raiseAlert(String origin, String msg, Object obj);
    
}
