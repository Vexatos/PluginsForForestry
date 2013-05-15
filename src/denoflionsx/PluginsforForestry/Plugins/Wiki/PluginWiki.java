package denoflionsx.PluginsforForestry.Plugins.Wiki;

import denoflionsx.PluginsforForestry.API.Plugin.IPfFPlugin;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Plugins.Wiki.Items.WikiBook;
import denoflionsx.PluginsforForestry.Plugins.Wiki.Items.WikiItems;

public class PluginWiki implements IPfFPlugin {

    @Override
    public void onPreLoad() {
    }

    @Override
    public void onLoad() {
        WikiItems.general = new WikiBook("denoflions", "PfF Documentation", "general.txt");
        PfF.Proxy.ItemCollections.add(WikiItems.class);
    }

    @Override
    public void onPostLoad() {
    }
}
