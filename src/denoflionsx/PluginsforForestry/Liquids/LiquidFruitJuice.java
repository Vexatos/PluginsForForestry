package denoflionsx.PluginsforForestry.Liquids;

import denoflionsx.PluginsforForestry.API.Annotations.SqueezeLiquid;
import denoflionsx.PluginsforForestry.API.Events.PfFEvent;
import denoflionsx.PluginsforForestry.API.Events.PfFSubscribe;
import denoflionsx.PluginsforForestry.Liquids.Generics.LiquidGenericEvent;
import denoflionsx.PluginsforForestry.gfx.PfFGfxPackage;

@SqueezeLiquid(liquidname = "Fruit Juice", validplants = {"Fruit"})
public class LiquidFruitJuice extends LiquidGenericEvent{

    public LiquidFruitJuice(String name, String[] textures) {
        super(name, textures);
    }

    public LiquidFruitJuice() {
        this("Fruit Juice", new String[]{PfFGfxPackage.getPackage() + "/liquids/juice_fruit.png", PfFGfxPackage.getPackage() + "/liquids/sparkles_fruit.png"});
    }

    @Override
    @PfFSubscribe
    public void onPfFEvent(PfFEvent event) {
        super.onPfFEvent(event);
    }
   
}
