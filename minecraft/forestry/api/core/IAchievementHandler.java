package forestry.api.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

public interface IAchievementHandler {
	void initialize();

	void itemPickup(EntityPlayer player, ItemStack itemstack);

	void itemCrafting(EntityPlayer player, ItemStack itemstack);
}
