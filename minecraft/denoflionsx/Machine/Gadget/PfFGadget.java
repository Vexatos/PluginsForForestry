package denoflionsx.Machine.Gadget;

import denoflionsx.API.Annotations.InternalUseOnly;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@InternalUseOnly
public class PfFGadget implements IPfFGadget{
    
    public ArrayList<PfFSlotObject> slots = new ArrayList();
    private int GuiID;
    private String name;
    private int maxInvSlots = 0;
    private String textureFile;
    private LinkedList triggers;

    public PfFGadget(int GuiID, String name, String TextureFile, PfFCustomTrigger[] triggers) {
        this.GuiID = GuiID;
        this.name = name;
        this.textureFile = TextureFile;
        this.triggers = new LinkedList(Arrays.asList(triggers));
        PfFGadgetManager.GadgetManager.registerGadget(this);
    }

    @Override
    public LinkedList getCustomTriggers() {
        return triggers;
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
