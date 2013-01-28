package denoflionsx.PluginsforForestry.Liquids.Generics;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFLiquid;
import denoflionsx.denLib.denLib;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;

public class LiquidImportBiogasFuel implements IPfFLiquid{
    
    private LiquidDictionary.LiquidRegisterEvent event;
    private int MJt;
    private int BurnTime;

    public LiquidImportBiogasFuel(LiquidRegisterEvent event, int MJt, int BurnTime) {
        this.event = event;
        this.MJt = MJt;
        this.BurnTime = BurnTime;
    }

    @Override
    public String getTag() {
        return denLib.toLowerCaseNoSpaces(this.event.Name);
    }

    @Override
    public IPfFLiquid createLiquid() {
        APIWrappers.forestry.biogas.addSafeFuel(event.Liquid, MJt, BurnTime);
        return this;
    }
}
