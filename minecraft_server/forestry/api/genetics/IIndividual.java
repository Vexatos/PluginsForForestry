package forestry.api.genetics;

import java.util.List;

import forestry.api.core.INBTTagable;


public interface IIndividual extends INBTTagable {

	boolean analyze();

	boolean isAnalyzed();

	int getMeta();

	String getDisplayName();

	void addTooltip(List list);

	boolean hasEffect();

	boolean isSecret();

	IGenome getGenome();

}
