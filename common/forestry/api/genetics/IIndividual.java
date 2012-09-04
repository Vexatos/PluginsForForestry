package forestry.api.genetics;

import java.util.List;

import forestry.api.core.INBTTagable;


public interface IIndividual extends INBTTagable {

	boolean analyze();

	boolean isAnalyzed();

	String getDisplayName();

	void addTooltip(List<String> list);

	boolean hasEffect();

	boolean isSecret();

	IGenome getGenome();

	String getIdent();

	/**
	 * @return true if the individual is originally of natural origin.
	 */
	boolean isNatural();
	/**
	 * @return generation this individual is removed from the original individual.
	 */
	int getGeneration();

	IIndividual setNatural(boolean flag);

	boolean isIrregularMating();

}
