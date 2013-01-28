package denoflionsx.PluginsforForestry_PluginTweaks.Proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IPfFTweakProxy {
    
    public void changeBlockLight(Block block, float light);
    
    public void changeMaxStack(Item item, int max);
    
}
