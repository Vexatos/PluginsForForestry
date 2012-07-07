package forestry.api.arboriculture;

import java.util.HashMap;

import forestry.api.genetics.IAlleleSpecies;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public interface IAlleleTreeSpecies extends IAlleleSpecies {
	HashMap<ItemStack, Integer> getProducts(World world, int biomeid, int x, int y, int z);

	public ItemStack[] getWoodBlocks(World world, int biomeid, int x, int y, int z);

	public WorldGenerator getTreeGenerator(EnumGrowthConditions conditions, int height);
}
