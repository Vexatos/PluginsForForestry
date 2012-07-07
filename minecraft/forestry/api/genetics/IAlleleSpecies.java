package forestry.api.genetics;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import net.minecraft.src.Achievement;

public interface IAlleleSpecies extends IAllele {
	/**
	 * @return Short, human-readable identifier used in tooltips and beealyzer.
	 */
	String getName();

	/**
	 * @return Color to use for the bee's border
	 */
	int getPrimaryColor();

	/**
	 * @return Anything other than 0xffffff will look strange!
	 */
	int getSecondaryColor();

	/**
	 * @return Preferred temperature
	 */
	EnumTemperature getTemperature();

	/**
	 * @return Preferred humidity
	 */
	EnumHumidity getHumidity();

	/**
	 * @return true if the species icon should have a glowing effect.
	 */
	boolean hasEffect();

	/**
	 * @return true if the species should not be displayed in NEI or creative inventory.
	 */
	boolean isSecret();

	/**
	 * @return true to have the species count against the species total.
	 */
	boolean isCounted();

	/**
	 * Binomial name of the species sans genus ("Apis"). Returning "humboldti" will have the bee species flavour name be "Apis humboldti". Feel free to use fun
	 * names or return null.
	 * 
	 * @return flavour text
	 */
	String getBinomial();

	Achievement getAchievement();
}
