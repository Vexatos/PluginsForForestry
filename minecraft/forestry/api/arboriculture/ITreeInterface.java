package forestry.api.arboriculture;

import net.minecraft.src.ItemStack;

public interface ITreeInterface {
	boolean isGermling(ItemStack itemstack);

	boolean isPollen(ItemStack itemstack);

	boolean isPollinated(ItemStack itemstack);

	ITree getTree(ItemStack itemstack);
}
