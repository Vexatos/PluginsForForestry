package forestry.api.genetics;

public interface IAlleleEffect extends IAllele
{
    boolean isCombinable();

    IEffectData validateStorage(IEffectData var1);

    String getIdentifier();
}
