package forestry.api.genetics;

import forestry.api.core.ForestryAPI;
import net.minecraft.src.ModLoader;

public class AlleleManager {

	public static IAllele[] alleleList = new IAllele[2048];

	/**
	 * Access Forestry's default alleles via reflection.
	 * 
	 * @param ident
	 * @return IAllele for the passed identifier or null if none was found.
	 */
	public static IAllele getAllele(String ident) {
		IAllele allele = null;

		try {
			
            String pack = ForestryAPI.class.getPackage().getName();
            String alleleClass = "forestry.core.genetics.Allele";
            if(pack.startsWith("net")){
                alleleClass = "net.minecraft.src." + alleleClass;
            }
            
			Object obj = Class.forName(alleleClass).getField(ident).get(null);
			if (obj instanceof IAllele)
				allele = (IAllele) obj;
		} catch (Exception ex) {
			ModLoader.getLogger().warning("Could not retrieve bee allele identified by: " + ident);
		}

		return allele;
	}

	/*
	 * 
	 * /// SPECIES (IAlleleSpecies, use ids 64 - 127 for your own)
	 * 
	 * // Common Branch speciesForest 0 speciesMeadows 1 speciesCommon 2 speciesCultivated 3
	 * 
	 * // Noble Branch speciesNoble 4 speciesMajestic 5 speciesImperial 6
	 * 
	 * // Industrious Branch speciesDiligent 7 speciesUnweary 8 speciesIndustrious 9
	 * 
	 * // ?? Branch ??? 10 ??? 11 ??? 12
	 * 
	 * // Infernal Branch speciesSinister 13 speciesFiendish 14 speciesDemonic 15
	 * 
	 * // Austere Branch speciesModest 16 speciesFrugal 17 speciesAustere 18
	 * 
	 * // Tropical Branch speciesTropical 19 {jungle1 20} {jungle2 21}
	 * 
	 * // ?? Branch species??? 22 ??? 23 ??? 24
	 * 
	 * // Frozen Branch speciesWintry 25 {speciesIcy} 26 {speciesGlacial} 27
	 * 
	 * // GENERIC (AlleleBoolean) boolFalse boolTrue
	 * 
	 * /// SPEED 1100 - 1199 (AlleleFloat) speedSlowest speedSlower speedSlow speedNorm speedFast speedFaster speedFastest
	 * 
	 * /// LIFESPAN 1200 - 1299 (AlleleInteger) lifespanShortest lifespanShorter lifespanShort lifespanShortened lifespanNormal lifespanElongated lifespanLong
	 * lifespanLonger lifespanLongest
	 * 
	 * /// FERTILITY 1300 - 1349 (AlleleInteger) fertilityLow fertilityNormal fertilityHigh fertilityMaximum
	 * 
	 * /// HUMIDITY 1400 - 1449 (AlleleHumidity) humidityNormal humidityArid humidityDamp
	 * 
	 * /// TOLERANCE 1450 - 1499 (AlleleTolerance) toleranceNone toleranceBoth1 toleranceBoth2 toleranceBoth3 toleranceBoth4 toleranceBoth5 toleranceUp1
	 * toleranceUp2 toleranceUp3 toleranceUp4 toleranceUp5 toleranceDown1 toleranceDown2 toleranceDown3 toleranceDown4 toleranceDown5
	 * 
	 * /// FLOWER PROVIDERS 1500 - 1699 (AlleleFlowers) flowersVanilla flowersNether flowersCacti flowersMushrooms flowersEnd flowersJungle flowersSnow
	 * 
	 * /// FLOWER GROWTH 1700 - 1749 (AlleleInteger) floweringSlowest floweringSlower floweringSlow floweringMaximum
	 * 
	 * /// TERRITORY 1750 - 1799 (AlleleArea) territoryDefault territoryLarge territoryLarger territoryLargest
	 * 
	 * /// EFFECTS 1800 - 1899 (IAlleleEffect, use ids 1900 - 1999 for your own) effectNone effectAggressive effectHeroic effectBeatific effectMiasmic
	 * effectMisanthrope effectRadioactive
	 */

}
