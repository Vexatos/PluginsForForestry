package denoflionsx.PluginsforForestry.Utils;

import denoflionsx.PluginsforForestry.Core.PfF;

public class UtilsTemplate {

    private boolean alert = false;
    private String api;

    public UtilsTemplate(String api) {
        this.api = api;
    }

    protected void doAlert() {
        if (!alert) {
            PfF.Proxy.warning(api + " not found!");
            alert = true;
        }
    }
}
