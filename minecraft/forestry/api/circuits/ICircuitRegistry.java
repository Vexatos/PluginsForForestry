package forestry.api.circuits;

import java.util.HashMap;
import net.minecraft.src.World;

public interface ICircuitRegistry
{
    HashMap getRegisteredCircuits();

    void registerCircuit(ICircuit var1);

    ICircuit getCircuit(String var1);

    ICircuitLibrary getCircuitLibrary(World var1, String var2);

    void registerLegacyMapping(int var1, String var2);

    ICircuit getFromLegacyMap(int var1);
}
