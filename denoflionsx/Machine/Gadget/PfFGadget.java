package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;
import java.util.ArrayList;

@InternalUseOnly
public class PfFGadget implements IPfFGadget{
    
    public ArrayList<PfFSlotObject> slots = new ArrayList();
    private int GuiID;
    private String name;
    private int maxInvSlots = 0;
    private String textureFile;

    public PfFGadget(int GuiID, String name, String TextureFile) {
        this.GuiID = GuiID;
        this.name = name;
        this.textureFile = TextureFile;
        PfFGadgetManager.GadgetManager.registerGadget(this);
    }

    @Override
    public int getGuiID() {
        return GuiID;
    }

    @Override
    public String getTextureFile() {
        return this.textureFile;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void registerSlot(int slotnum, int x, int y) {
        slots.add(new PfFSlotObject(slotnum,x,y));
        this.maxInvSlots++;
    }

    @Override
    public ArrayList getSlots() {
        return slots;
    }

    @Override
    public int getInventorySize() {
        return this.maxInvSlots;
    } 
}
