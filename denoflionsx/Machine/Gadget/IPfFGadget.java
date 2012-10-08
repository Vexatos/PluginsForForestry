package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.ArrayList;

@InternalUseOnly
public interface IPfFGadget {
    
    public String getName();
    
    public int getGuiID();
    
    public int getInventorySize();
    
    public String getTextureFile();
    
    public void registerSlot(int slotnum, int x, int y);
    
    public ArrayList getSlots();
    
}
