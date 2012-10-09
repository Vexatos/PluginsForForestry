package forestry.api.genetics;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

/**
 * Can be used to garner information on bee breeding and to register new bees. See {@link IBreedingManager}
 * 
 * @author SirSengir
 */
public interface IApiaristTracker {

	void decodeFromNBT(NBTTagCompound nbttagcompound);
	void encodeToNBT(NBTTagCompound nbttagcompound);

	String getModeName();
	
	void setModeName(String name);
	
	void registerQueen(EntityPlayer player, IIndividual bee);

	int getQueenCount();

	void registerPrincess(EntityPlayer player, IIndividual bee);

	int getPrincessCount();

	void registerDrone(EntityPlayer player, IIndividual bee);

	int getDroneCount();

	/**
	 * @return Integer denoting the amount of species discovered.
	 */
	int getSpeciesBred();

	void registerMutation(IMutation mutation);

	boolean isDiscovered(IMutation mutation);

	boolean isDiscovered(IAlleleSpecies species);

}
