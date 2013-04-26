package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;

public class Credits implements IdenWorldEventHandler {

    @Override
    public void onWorldEnded() {
    }

    @Override
    public void onWorldLoaded() {
        for (String s : denLib.StringUtils.readFileContentsAutomated(PfF.core.configDir, "Credits.txt", this)){
            PfF.Proxy.print(s);
        }
        WorldEventHandler.unregisterHandler(this);
    }
}
