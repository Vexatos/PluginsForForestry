package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidGenericBiogasFuel extends LiquidGeneric {

    private int MJt;
    private int BurnTime;

    public LiquidGenericBiogasFuel(int MJt, int BurnTime, String name, String[] textures) {
        super(name, textures);
        this.MJt = MJt;
        this.BurnTime = BurnTime;
    }

    @Override
    public IPfFLiquid createLiquid() {
        super.createLiquid();
        APIWrappers.forestry.biogas.addSafeFuel(liquid, MJt, BurnTime);
        EngineFuel f = new EngineFuel(MJt,BurnTime,liquid);
        PfFManagers.Events.SystemEvents.raiseAlert("Fuel", this.name, f);
        return this;
    }
    
    public static class EngineFuel{
        
        private int MJt;
        private int BurnTime;
        private LiquidStack liquid;

        public EngineFuel(int MJt, int BurnTime, LiquidStack liquid) {
            this.MJt = MJt;
            this.BurnTime = BurnTime;
            this.liquid = liquid;
        }

        public int getBurnTime() {
            return BurnTime;
        }

        public int getMJt() {
            return MJt;
        }

        public LiquidStack getLiquid() {
            return liquid;
        }
    }
}
