package denoflionsx.plugins.BlueSilkWorm.Gadget;

import denoflionsx.Machine.Gadget.PfFGadget;
import denoflionsx.plugins.Buildcraft.Triggers.PfFCustomTrigger;
import denoflionsx.plugins.Buildcraft.Triggers.Triggers;

public class GadgetIncubator extends PfFGadget{
    
    public static final GadgetIncubator incubator = new GadgetIncubator(0,"Incubator",new PfFCustomTrigger[]{Triggers.hasCocoon,Triggers.hasMoth,Triggers.hasWork});
    public static final String texture = "/denoflionsx/incubator_gui.png";
    
    public GadgetIncubator(int GuiID, String name, PfFCustomTrigger[] triggers) {
        super(GuiID, name,texture, triggers);
        this.registerSlot(0, 8, 17);
        this.registerSlot(1, 26, 17);
        this.registerSlot(2, 44, 17);
        this.registerSlot(3, 8, 35);
        this.registerSlot(4, 26, 35);
        this.registerSlot(5, 44, 35);
        
        this.registerSlot(6, 65, 17);
        this.registerSlot(7, 65, 35);
        
        this.registerSlot(8, 86, 27);
        this.registerSlot(9, 116, 27);
        
        this.registerSlot(10, 152, 9);
        this.registerSlot(11, 152, 27);
        
        this.registerSlot(12, 8, 59);
        this.registerSlot(13, 26, 59);
        this.registerSlot(14, 44, 59);
        this.registerSlot(15, 62, 59);
        this.registerSlot(16, 80, 59);
        this.registerSlot(17, 98, 59);
        this.registerSlot(18, 116, 59);
        this.registerSlot(19, 134, 59);
        this.registerSlot(20, 152, 59);
    }

}
