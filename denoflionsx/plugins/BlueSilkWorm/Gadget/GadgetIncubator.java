package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Machine.Gadget.PfFGadget;

public class GadgetIncubator extends PfFGadget{
    
    public static GadgetIncubator incubator = new GadgetIncubator(0,"Incubator");
    public static final String texture = "/denoflionsx/incubator_gui.png";
    
    public GadgetIncubator(int GuiID, String name) {
        super(GuiID, name,texture);
        this.registerSlot(0, 76, 37);
    }

}
