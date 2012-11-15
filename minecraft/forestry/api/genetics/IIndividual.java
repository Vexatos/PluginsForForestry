package forestry.api.genetics;

import forestry.api.core.INBTTagable;
import java.util.List;

public interface IIndividual extends INBTTagable
{
    boolean analyze();

    boolean isAnalyzed();

    String getDisplayName();

    void addTooltip(List var1);

    boolean hasEffect();

    boolean isSecret();

    IGenome getGenome();

    String getIdent();

    boolean isNatural();

    int getGeneration();

    IIndividual setNatural(boolean var1);

    boolean isIrregularMating();
}
