package forestry.api.arboriculture;

import net.minecraft.src.World;

public interface IGrowthProvider {
	EnumGrowthConditions getGrowthConditions(World world, int biomeid, int xPos, int yPos, int zPos);
}
