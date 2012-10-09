package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.HashMap;

@InternalUseOnly
public class PfFGadgetManager implements IPfFGadgetManager{
    
    private static HashMap<String, IPfFGadget> gadgets = new HashMap();
    public static IPfFGadgetManager GadgetManager = new PfFGadgetManager();

    @Override
    public IPfFGadget getGadgetByName(String name) {
        if (gadgets.get(name) != null){
            return gadgets.get(name);
        }else{
            return null;
        }
    }

    @Override
    public void registerGadget(IPfFGadget gadget) {
        gadgets.put(gadget.getName(),gadget);
    } 
}
