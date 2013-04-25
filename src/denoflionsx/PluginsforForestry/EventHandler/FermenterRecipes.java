package denoflionsx.PluginsforForestry.EventHandler;

import denoflionsx.PluginsforForestry.Utils.FermenterUtils;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.WorldEventHandler;
import java.util.ArrayList;
import net.minecraftforge.liquids.LiquidStack;

public class FermenterRecipes implements IdenWorldEventHandler {
    
    public static ArrayList<Fermentable> fermentables = new ArrayList();
    
    public static void register(){
        IdenWorldEventHandler handler = new FermenterRecipes();
        WorldEventHandler.registerHandler(handler);
    }
    
    @Override
    public void onWorldEnded() {
    }
    
    @Override
    public void onWorldLoaded() {
        for (Fermentable l : fermentables) {
            FermenterUtils.registerFermenterBooster(l.getLiquid(), l.getBonus());
        }
        WorldEventHandler.unregisterHandler(this);
    }
    
    public static class Fermentable {
        
        private LiquidStack liquid;
        private float bonus;
        
        public Fermentable(LiquidStack liquid, float bonus) {
            this.liquid = liquid;
            this.bonus = bonus;
        }
        
        public float getBonus() {
            return bonus;
        }
        
        public LiquidStack getLiquid() {
            return liquid;
        }
    }
}
