package denoflionsx.API.Interfaces;

public interface IPfFPlugin {
    
    // New plugin interface.
    
    public void register();
    
    public boolean init();
    
    public void doSetup();
    
    public void recipes();
    
    public String getName();
    
    public String getParentName();
    
    public boolean isLoaded();
    
    public void setLoadedState(boolean state);
    
    public void defaults();
    
    // This object will be a Config object from denLib.
    public Object configAccess();
    
}
