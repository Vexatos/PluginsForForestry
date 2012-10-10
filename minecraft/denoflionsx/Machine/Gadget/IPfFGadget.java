package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.ArrayList;
import java.util.LinkedList;
import net.minecraft.src.IInventory;

@InternalUseOnly
public interface IPfFGadget {
    
    public String getName();
    
    public int getGuiID();
    
    public int getInventorySize();
    
    public String getTextureFile();
    
    public void updateCommon(IInventory inventory);
    
    public void updateClientOnly(IInventory inventory);
    
    public LinkedList getCustomTriggers();
    
    public void registerSlot(int slotnum, int x, int y);
    
    public ArrayList getSlots();
    
}
