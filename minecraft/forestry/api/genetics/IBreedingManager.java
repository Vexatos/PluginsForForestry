package forestry.api.genetics;

import net.minecraft.src.World;

public interface IBreedingManager {

	void registerBeeTemplate(IAllele[] template);

	/**
	 * @param world
	 * @return {@link IApiaristTracker} associated with the passed world.
	 */
	IApiaristTracker getApiaristTracker(World world);

	IWoodsmanTracker getWoodsmanTracker(World world);
}
