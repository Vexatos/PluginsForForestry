package forestry.api.apiculture;

import java.util.ArrayList;

import forestry.api.genetics.IBreedingManager;
import forestry.api.genetics.IMutation;


public class BeeManager {

	public static int beeSpeciesCount = 0;
	public static IBeeInterface beeInterface;

	/**
	 * Species templates for bees that can drop from hives.
	 * 
	 * 0 - Forest 1 - Meadows 2 - Desert 3 - Jungle 4 - End 5 - Snow 6 - Swamp
	 * 
	 * see {@link IMutation} for template format
	 */
	public static ArrayList<IHiveDrop>[] hiveDrops;

	/**
	 * Access to Forestry's breeding manager for breeding information.
	 */
	public static IBreedingManager breedingManager;
	/**
	 * List of possible mutations on species alleles.
	 */
	public static ArrayList<IMutation> beeMutations = new ArrayList<IMutation>();
}
