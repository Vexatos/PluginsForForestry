package denoflionsx.PluginsforForestry.API;

import denoflionsx.PluginsforForestry.API.Events.IPfFEventManager;
import denoflionsx.PluginsforForestry.API.Interfaces.*;

public class PfFManagers {

    public static IPfFItemManager Items;
    public static IPfFLiquidManager Liquids;
    public static IPfFLiquidVacuumManager LiquidVacuum;
    public static IPfFOmniPlantManager OmniPlant;
    public static IPfFSqueezableManager Squeeze;
    public static IPfFItemManager Foods;
    public static IPfFLiquidVacuumManager ButcherKnife;

    public static class Events {

        public static IPfFEventManager SystemEvents;
    }
}
