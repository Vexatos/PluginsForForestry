package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Machine.Gadget.PfFGadget;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import denoflionsx.plugins.Buildcraft.Triggers.Triggers;

public class GadgetIncubator extends PfFGadget{
    
    public static final GadgetIncubator incubator = new GadgetIncubator(0,"Incubator");
    public static final String texture = "/denoflionsx/incubator_gui.png";
    
    public GadgetIncubator(int GuiID, String name) {
        super(GuiID, name,texture, new PfFCustomTrigger[]{Triggers.hasCocoon,Triggers.hasMoth});
        this.registerSlot(0, 76, 37);
    }

}
