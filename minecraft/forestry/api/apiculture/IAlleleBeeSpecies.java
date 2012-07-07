package forestry.api.apiculture;

import java.util.HashMap;

import forestry.api.genetics.IAlleleSpecies;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IAlleleBeeSpecies extends IAlleleSpecies {

	// / Products, Chance
	HashMap<ItemStack, Integer> getProducts();

	// / Specialty, Chance
	HashMap<ItemStack, Integer> getSpecialty();

	// / Only jubilant bees give their specialty product
	boolean isJubilant(World world, int biomeid, int x, int y, int z);

}
