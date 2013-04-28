package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Handlers;

import java.util.ArrayList;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketEventRegistry {
    
    private ArrayList<IBucketEventHandler> handlers = new ArrayList();
    
    public void registerHandler(IBucketEventHandler handler){
        handlers.add(handler);
    }
    
    public void runEvent(FillBucketEvent e){
        for (IBucketEventHandler handler : handlers){
            handler.onBucketFill(e);
        }
    }
    
}
