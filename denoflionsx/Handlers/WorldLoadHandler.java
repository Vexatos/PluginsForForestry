package denoflionsx.Handlers;

import denoflionsx.plugins.PluginRegistry;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class WorldLoadHandler {

    public boolean hasRan = false;

    @ForgeSubscribe
    public void onWorldLoaded(WorldEvent.Load event) {
        if (!hasRan) {
            PluginRegistry.registerLatePlugins();
            hasRan = true;
        }
    }
}
