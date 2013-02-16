package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericEvent;
import denoflionsx.PluginsforForestry.Utils.PfFConstants;

@SqueezeLiquid(liquidname = "Melon Juice", validplants = "Melon")
public class LiquidMelonJuice extends LiquidGenericEvent {

    public LiquidMelonJuice(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidMelonJuice() {
        super("Melon Juice", new String[]{PfFConstants.PfFPath + "juice_melon.png", PfFConstants.PfFPath + "sparkles_melon.png"});
    }

    @Override
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        super.onPfFEvent(event);
    }
}
