package forestry.api.arboriculture;

import net.minecraft.src.World;

public interface IGrowthProvider
{
    EnumGrowthConditions getGrowthConditions(World var1, int var2, int var3, int var4);
}
