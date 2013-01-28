package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;

public class LiquidGenericEvent extends LiquidGeneric {

    private int eventID;
    private boolean init = false;

    public LiquidGenericEvent(String name, String[] textures) {
        super(name, textures);
        this.register();
    }

    @Override
    public IPfFLiquid createLiquid() {
        init = true;
        return super.createLiquid();
    }

    public final void register() {
        eventID = PfFManagers.Events.SystemEvents.registerListener(this);
    }

    public final void unregister() {
        PfFManagers.Events.SystemEvents.unregisterListener(eventID);
    }

    // THIS ANNOTATION CANNOT BE INHERITED!
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        if (!init){
            if (event.getMsg().equals(name)){
                this.createLiquid();
                this.unregister();
                PfFManagers.Events.SystemEvents.raiseAlert(name, "ref", this);
            }
        }
    }
}
