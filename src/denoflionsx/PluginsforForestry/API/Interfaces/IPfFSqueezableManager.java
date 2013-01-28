package denoflionsx.PluginsforForestry.API.Interfaces;

import denoflionsx.PluginsforForestry.API.Objects.DictMap;
import denoflionsx.PluginsforForestry.API.Objects.DictMap.Dict;
import java.util.List;
import net.minecraft.item.ItemStack;

public interface IPfFSqueezableManager {
    
    /*
     * Sends Two PfFEvents:
     * registerSqueezeLiquid:
     * origin: SqueezeManager|registerSqueezeLiquid
     * msg: class name of liquid
     * obj: LiquidStack
     * 
     * registerSqueezable:
     * origin: SqueezeManager|registerSqueezable
     * msg: registered name
     * obj: Dict
     */
    
    /*
     * For Veggie juice you can register under the generic name "Veggie".
     * For Fruit juice you can register under the generic name "Fruit".
     */
    
    public void registerSqueezable(IPfFSqueezable squeezeme);
    
    public void registerSqueezable(String name, ItemStack squeezeme, int liquidAmount);
    
    public void registerSqueezeLiquid(IPfFLiquid liquid);
    
    public DictMap getMap();
    
    public List<Dict> getSqueezablesForName(String name);
    
}
