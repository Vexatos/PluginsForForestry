package denoflionsx.Handlers;

import denoflionsx.denLib.FMLWrapper;

public class HandlerInstances {

    public FuelHandler Fuel = new FuelHandler();
    public OreDictHandler Ore = new OreDictHandler();
    public WorldLoadHandler World = new WorldLoadHandler();

    public HandlerInstances() {
        FMLWrapper.MODE.FML.registerFuelHandler(Fuel);
        FMLWrapper.MODE.FML.registerEvent(Ore);
        FMLWrapper.MODE.FML.registerEvent(World);
    }
}
