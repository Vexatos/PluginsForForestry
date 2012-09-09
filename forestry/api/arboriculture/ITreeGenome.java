package forestry.api.arboriculture;

import forestry.api.genetics.EnumTolerance;
import forestry.api.genetics.IGenome;

public interface ITreeGenome extends IGenome {

	IAlleleTreeSpecies getPrimaryAsTree();

	IAlleleTreeSpecies getSecondaryAsTree();

	IGrowthProvider getGrowthProvider();

	int getHeight();

	float getFertility();

	float getYield();

	EnumTolerance getToleranceTemp();

	EnumTolerance getToleranceHumid();

}
