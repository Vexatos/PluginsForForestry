package denoflionsx.plugins.Railcraft.Event;

import buildcraft.api.liquids.LiquidStack;
import denoflionsx.API.Annotations.PfFEventTypes;
import denoflionsx.API.Annotations.PfFSubscribe;
import denoflionsx.API.Events.EventFuelCreated;
import denoflionsx.API.PfFEvents;
import railcraft.common.api.fuel.FuelManager;

public class BoilerFuelHandler {

    @PfFSubscribe(Event = PfFEventTypes.FUEL)
    public void onFuelCreated(EventFuelCreated event){
        FuelManager.addBoilerFuel(new LiquidStack(event.getFuel().getLiquid().itemID,1000),FuelCalculation.getFuelValue(event.getFuel().getMJt(),event.getFuel().getBurnTime()));
    }
    
    public BoilerFuelHandler registerWithAPI(){
        PfFEvents.fuelEvent.register(this);
        return this;
    }
    
}
