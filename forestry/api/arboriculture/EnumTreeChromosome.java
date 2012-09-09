package forestry.api.arboriculture;

public enum EnumTreeChromosome {

	SPECIES, // Determines preferred temperature, humidity, used wood/leaf
				// blocks, used worldgen, fruits
	GROWTH, // IGrowthProvider, determines growth conditions
	HEIGHT, // Max height of grown tree
	SAPLINGS, // Chance for saplings
	YIELD, // Chance for fruit leaves / drops
	TEMPERATURE_TOLERANCE,
	HUMIDITY_TOLERANCE,
	TERRITORY,
	EFFECT // Effect on
			// leaves

}
