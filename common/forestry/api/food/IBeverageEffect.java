package forestry.api.food;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public interface IBeverageEffect {
	int getId();

	void doEffect(World world, EntityPlayer player);

	String getDescription();
}
