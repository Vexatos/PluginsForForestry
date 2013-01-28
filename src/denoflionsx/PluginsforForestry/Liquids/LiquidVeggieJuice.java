package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericEvent;
import denoflionsx.PluginsforForestry.gfx.PfFGfxPackage;

@SqueezeLiquid(liquidname = "Veggie Juice", validplants = {"Potato", "Carrot", "Veggie"})
public class LiquidVeggieJuice extends LiquidGenericEvent {

    public LiquidVeggieJuice(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidVeggieJuice() {
        this("Veggie Juice", new String[]{PfFGfxPackage.getPackage() + "/liquids/juice_veggie.png", PfFGfxPackage.getPackage() + "/liquids/sparkles_veggie.png"});
    }

    @Override
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        super.onPfFEvent(event);
    }

    

}
