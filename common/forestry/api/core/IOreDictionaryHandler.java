package forestry.api.core;

import net.minecraft.src.ItemStack;

public interface IOreDictionaryHandler {
	void onOreRegistration(String name, ItemStack ore);
}
