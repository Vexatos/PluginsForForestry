package denoflionsx.Managers;

import denoflionsx.API.IPfFSolidFuelRegistry;
import java.util.HashMap;

public class PfFSolidFuelManager implements IPfFSolidFuelRegistry {

    private static HashMap<Integer, Integer> fuels = new HashMap();

    @Override
    public void addFuel(int id, int burn) {
        fuels.put(id, burn);
        
    }

    @Override
    public int getFuelValue(int id) {
        if (fuels.get(id) != null) {
            return fuels.get(id);
        } else {
            return 0;
        }
    }
}
