package denoflionsx.API.Interfaces;

public interface IPfFPlugin {
    
    // New plugin interface.
    
    public void register();
    
    public boolean init();
    
    public void recipes();
    
    public String getName();
    
    public boolean isLoaded();
    
}
