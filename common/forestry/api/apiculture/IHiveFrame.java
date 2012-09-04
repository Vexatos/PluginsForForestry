package forestry.api.apiculture;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IHiveFrame extends IBeeModifier {

	/**
	 * Wears out a frame.
	 * @param world World object the caller and frame are in.
	 * @param frame ItemStack containing the actual frame.
	 * @param queen Current queen in the caller.
	 * @param wear Integer denoting the amount worn out. {@link IBeekeepingMode.getWearModifier()} has already been taken into account.
	 * @return ItemStack containing the actual frame with adjusted damage.
	 */
	ItemStack frameUsed(World world, ItemStack frame, IBee queen, int wear);

}
