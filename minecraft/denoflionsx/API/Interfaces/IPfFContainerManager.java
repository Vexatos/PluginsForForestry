package denoflionsx.API.Interfaces;

import denoflionsx.API.Annotations.InternalUseOnly;
import denoflionsx.API.Objects.PfFColor;
import denoflionsx.API.Objects.PfFLiquid;
import java.util.ArrayList;
import net.minecraft.src.ItemStack;

public interface IPfFContainerManager {
    
    // This one is for pre-defined colors in the color manager.
    public void addLiquid(String LiquidName, ItemStack liquid, PfFColor color);
    
    // This one is for custom colors. Pass RBG values as ints.
    public void addLiquid(String LiquidName, ItemStack liquid, int r, int g, int b);
    
    @InternalUseOnly
    public void forceLiquidSetup(String LiquidName);

    public ArrayList<PfFLiquid> getLiquids();
    
}
