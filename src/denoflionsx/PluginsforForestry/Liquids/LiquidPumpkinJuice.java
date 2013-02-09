package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericEvent;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;

@SqueezeLiquid(liquidname = "Pumpkin Juice", validplants = "Pumpkin")
public class LiquidPumpkinJuice extends LiquidGenericEvent {

    public LiquidPumpkinJuice(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidPumpkinJuice() {
        super("Pumpkin Juice", new String[]{PfFConstants.PfFPath + "juice_pumpkin.png", PfFConstants.PfFPath + "sparkles_pumpkin.png"});
    }

    @Override
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        super.onPfFEvent(event);
    }
}
