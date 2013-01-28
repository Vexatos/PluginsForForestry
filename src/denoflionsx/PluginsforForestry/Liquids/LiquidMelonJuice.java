package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericEvent;
import denoflionsx.PluginsforForestry.gfx.PfFGfxPackage;

@SqueezeLiquid(liquidname = "Melon Juice", validplants = "Melon")
public class LiquidMelonJuice extends LiquidGenericEvent{

    public LiquidMelonJuice(String name, String[] textures) {
        super(name, textures);
    }

    
    public LiquidMelonJuice() {
        super("Melon Juice", new String[]{PfFGfxPackage.getPackage() + "/liquids/juice_melon.png",PfFGfxPackage.getPackage() + "/liquids/sparkles_melon.png"});
    }

    @Override
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        super.onPfFEvent(event);
    }

}
