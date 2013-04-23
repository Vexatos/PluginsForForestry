package denoflionsx.PluginsforForestry.Proxy;

import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Blocks.LRLiquidBlock;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items.LRLiquidItem;

public interface IPfFProxy {
    
    public void print(String msg);
    
    public void registerClientSide();
    
    public void registerLiquidBlock(String name, LRLiquidBlock b);
    
    public void registerLiquidItem(String name, LRLiquidItem i);
    
}
