package forestry.api.apiculture;

import net.minecraft.src.ItemStack;

public interface IBeeInterface {
	/**
	 * @return true if passed item is one of Forestry's bees.
	 */
	boolean isBee(ItemStack stack);

	/**
	 * @return true if passed item is a drone.
	 */
	boolean isDrone(ItemStack stack);

	/**
	 * @return true if passed item is mated (i.e. a queen)
	 */
	boolean isMated(ItemStack stack);

	/**
	 * @return {@link IBee} pattern parsed from the passed stack's nbt data.
	 */
	IBee getBee(ItemStack stack);
}
