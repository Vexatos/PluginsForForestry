package forestry.api.circuits;

import net.minecraft.src.World;

public interface ICircuitManager {

	ICircuitLibrary getCircuitLibrary(World world, String playername);

}
