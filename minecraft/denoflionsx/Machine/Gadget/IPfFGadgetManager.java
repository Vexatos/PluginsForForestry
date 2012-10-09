package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;

@InternalUseOnly
public interface IPfFGadgetManager {
    
    public void registerGadget(IPfFGadget gadget);
    
    public IPfFGadget getGadgetByName(String name);
    
}
