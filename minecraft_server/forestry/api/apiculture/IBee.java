package forestry.api.apiculture;

import java.util.ArrayList;

import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * Other implementations than Forestry's default one are not supported.
 * 
 * @author SirSengir
 */
public interface IBee extends IIndividual {

	void age();

	void mate(IBee drone);

	IEffectData[] doEffect(IEffectData[] storedData, World world, int biomeid, int x, int y, int z);

	IEffectData[] doFX(IEffectData[] storedData, World world, int biomeid, int x, int y, int z);

	boolean isAlive();
	boolean isPureBred(EnumBeeChromosome chromosome);

	/**
	 * @return true if the bee may spawn offspring
	 */
	boolean canSpawn();

	/**
	 * Determines whether the queen can work.
	 * 
	 * @param world
	 * @param isAlveary
	 * @param biomeid
	 * @param temperature
	 * @param humidity
	 * @param x
	 * @param y
	 * @param z
	 * @return Ordinal of the error code encountered. 0 - EnumErrorCode.OK
	 */
	int isWorking(World world, boolean isAlveary, int biomeid, float temperature, float humidity, int x, int y, int z);

	boolean hasFlower(World world, int biomeid, int x, int y, int z);

	ArrayList<Integer> getSuitableBiomeIds();

	ItemStack[] getProduceList();

	ItemStack[] getSpecialtyList();

	ItemStack[] produceStacks(World world, int biomeid, int x, int y, int z);

	IBee spawnPrincess(World world, int biomeid, int x, int y, int z);

	IBee[] spawnDrones(World world, int biomeid, int x, int y, int z);

	void plantFlowerRandom(World world, int biomeid, int x, int y, int z);

	int getHealth();

	int getMaxHealth();

	IBeeGenome getGenome();

}
