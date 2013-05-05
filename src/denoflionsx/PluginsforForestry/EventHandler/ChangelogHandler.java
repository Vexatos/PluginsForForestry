package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Changelog.ChangelogReader;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;

public class ChangelogHandler implements IdenWorldEventHandler {

    @Override
    public void onWorldLoaded() {
        ChangelogReader.create();
        WorldEventHandler.unregisterHandler(this);
    }

    @Override
    public void onWorldEnded() {
    }
}
