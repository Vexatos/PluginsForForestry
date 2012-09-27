package denoflionsx.API.Interfaces;

public interface IPfFModule extends IPfFPlugin{
    
    // Modules are plugins that are completely dependant on another plugin.
    // They use their parent's config file.
    
    public IPfFPlugin getParent();
    
}
