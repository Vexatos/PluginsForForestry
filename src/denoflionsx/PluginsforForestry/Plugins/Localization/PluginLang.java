package denoflionsx.PluginsforForestry.Plugins.Localization;

import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;

public class PluginLang implements IPfFPlugin {
    
    @Override
    public void onLoad() {
    }
    
    @Override
    public void onPostLoad() {
        PfF.lang.extractLang(new String[]{"en_US"});
        PfF.lang.loadLang();
    }
    
    @Override
    public void onPreLoad() {
    }
}
