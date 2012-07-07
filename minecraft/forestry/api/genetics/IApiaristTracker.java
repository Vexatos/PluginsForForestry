package forestry.api.genetics;

import forestry.api.core.INBTTagable;
import net.minecraft.src.EntityPlayer;

/**
 * Can be used to garner information on bee breeding and to register new bees. See {@link IBreedingManager}
 * 
 * @author SirSengir
 */
public interface IApiaristTracker extends INBTTagable {

	void registerQueen(EntityPlayer player, IIndividual bee);

	int getQueenCount();

	void registerPrincess(EntityPlayer player, IIndividual bee);

	int getPrincessCount();

	void registerDrone(EntityPlayer player, IIndividual bee);

	int getDroneCount();

	int getSpeciesBred();

	void registerMutation(IMutation mutation);

	boolean isDiscovered(IMutation mutation);

	boolean isDiscovered(IAlleleSpecies species);

}
